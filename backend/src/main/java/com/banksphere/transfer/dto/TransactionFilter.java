package com.banksphere.transfer.dto;

import com.banksphere.customer.enums.TransactionDirection;
import com.banksphere.transfer.entity.enums.TransferStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TransactionFilter(
        TransactionDirection direction,
        TransferStatus status,
        LocalDate fromDate,
        LocalDate toDate
) {
}
