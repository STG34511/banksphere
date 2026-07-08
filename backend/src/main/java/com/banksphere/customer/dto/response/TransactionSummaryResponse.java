package com.banksphere.customer.dto.response;

import com.banksphere.customer.enums.TransactionDirection;
import com.banksphere.transfer.entity.enums.TransferStatus;
import com.banksphere.transfer.entity.enums.TransferType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionSummaryResponse(
        UUID transferId,
        String referenceNumber,
        TransferType transferType,
        TransactionDirection direction,
        String counterpartyName,
        BigDecimal amount,
        TransferStatus status,
        LocalDateTime transactionDate
) {
}
