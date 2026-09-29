package com.banksphere.transfer.service;

import com.banksphere.transfer.dto.response.TransferResponse;
import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferStatus;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {

    public TransferResponse toResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getReferenceNumber(),
                transfer.getStatus(),
                transfer.getAmount(),
                transfer.getTransferMode(),
                transfer.getCreatedAt(),
                transfer.getCompletedAt(),
                transfer.getRemarks()
        );
    }

    public TransferResponse toInitiatedResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getReferenceNumber(),
                TransferStatus.INITIATED,
                transfer.getAmount(),
                transfer.getTransferMode(),
                transfer.getCreatedAt(),
                transfer.getCompletedAt(),
                transfer.getRemarks()
        );
    }
}
