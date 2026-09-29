package com.banksphere.transfer.service;

import com.banksphere.account.entity.Account;
import com.banksphere.common.service.ReferenceGenerationService;
import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferStatus;
import com.banksphere.transfer.entity.enums.TransferType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferFactory {

    private final ReferenceGenerationService referenceGenerationService;

    public Transfer createCustomerTransfer(
            Account sourceAccount,
            Account destinationAccount,
            TransferRequest request
    ) {
        Transfer transfer = new Transfer();

        transfer.setReferenceNumber(
                referenceGenerationService.generateTransferReference()
        );

        transfer.setSourceAccount(sourceAccount);
        transfer.setDestinationAccount(destinationAccount);

        transfer.setBeneficiaryAccountNumber(
                request.beneficiaryDetails().beneficiaryAccountNumber()
        );

        transfer.setBeneficiaryName(
                request.beneficiaryDetails().beneficiaryName()
        );

        transfer.setBeneficiaryIfsc(
                request.beneficiaryDetails().beneficiaryIfsc()
        );

        transfer.setTransferMode(request.transferMode());
        transfer.setAmount(request.amount());
        transfer.setIdempotencyKey(request.idempotencyKey());

        transfer.setTransferType(TransferType.CUSTOMER_TRANSFER);
        transfer.setFeeAmount(BigDecimal.ZERO);
        transfer.setRemarks(request.remarks());
        transfer.setStatus(TransferStatus.INITIATED);
        transfer.setRetryCount(0);

        return transfer;
    }
}
