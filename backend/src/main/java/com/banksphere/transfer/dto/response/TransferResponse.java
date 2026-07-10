package com.banksphere.transfer.dto.response;

import com.banksphere.transfer.entity.enums.TransferMode;
import com.banksphere.transfer.entity.enums.TransferStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferResponse(

        String referenceNumber,

        TransferStatus status,

        BigDecimal amount,

        TransferMode transferMode,

        LocalDateTime initiatedAt,

        LocalDateTime completedAt,

        String message

) {
}
