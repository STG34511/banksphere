package com.banksphere.accounting.kafka;

import com.banksphere.accounting.service.PaymentSettlementService;
import com.banksphere.payment.dto.PaymentSettlementBatch;
import com.banksphere.payment.dto.PaymentSettlementInstruction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSettlementConsumer {

    private final PaymentSettlementService paymentSettlementService;

    @KafkaListener(topicPattern = "neft.process", groupId = "neft.consumers")
    public void consume(PaymentSettlementBatch paymentSettlementBatch) {
        for (PaymentSettlementInstruction instruction : paymentSettlementBatch.instructions()) {
            paymentSettlementService.process(instruction);
        }
    }


}
