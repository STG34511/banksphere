package com.banksphere.transfer.dto;

import com.banksphere.customer.enums.TransactionDirection;
import com.banksphere.transfer.entity.enums.TransferStatus;

import java.time.LocalDate;

public record TransactionFilter(
        TransactionDirection direction,
        TransferStatus status,
        LocalDate fromDate,
        LocalDate toDate
) {
}
