package com.banksphere.payment.service.neft;

import com.banksphere.payment.dto.PaymentSettlementBatch;
import com.banksphere.payment.dto.PaymentSettlementInstruction;
import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferMode;
import com.banksphere.transfer.entity.enums.TransferStatus;
import com.banksphere.transfer.repository.TransferRepository;
import com.banksphere.transfer.service.TransferLifecycleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class NEFTProcessingService {

    private final TransferRepository transferRepository;
    private final TransferLifecycleService transferLifecycleService;
    private final KafkaTemplate<String, PaymentSettlementBatch> kafkaTemplate;

    public void process(List<Transfer> transfers) {

        List<PaymentSettlementInstruction> neftSettlementInstructions = transfers.stream().map(transfer ->
                new PaymentSettlementInstruction(transfer.getReferenceNumber(), transfer.getAmount(),
                        transfer.getBeneficiaryAccountNumber(), transfer.getBeneficiaryName(),
                        transfer.getBeneficiaryIfsc())).toList();

        PaymentSettlementBatch paymentBatch = new PaymentSettlementBatch("", TransferMode.NEFT, neftSettlementInstructions);

        CompletableFuture<SendResult<String, PaymentSettlementBatch>> neftTransferEvent =
                kafkaTemplate.send("neft.process", paymentBatch);
        neftTransferEvent.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println(ex.getMessage());
                return;
            }
            System.out.println("Transfer sent to neft.process");
        });


    }

    @Transactional
    public List<Transfer> pickTransfer() {
        Pageable pageable = PageRequest.of(0, 500, Sort.by(Sort.Direction.ASC, "createdAt"));

        Page<Transfer> transfers = transferRepository.findByTransferModeAndStatus(TransferMode.NEFT, TransferStatus.PROCESSING, pageable);

        List<Transfer> transferList = transfers.getContent();
        transferList.forEach(transferLifecycleService::markPicked);

        return transferList;
    }
}
