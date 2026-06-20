package com.banksphere.customer.dto.response;

import com.banksphere.account.enums.AccountStatus;
import com.banksphere.account.enums.AccountType;

import java.math.BigDecimal;

public record AccountSummaryResponse(
        String accountNumber,
        AccountType accountType,
        AccountStatus status,
        BigDecimal balance,
        boolean primary
) {
}
