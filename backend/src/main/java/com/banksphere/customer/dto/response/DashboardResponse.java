package com.banksphere.customer.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record DashboardResponse(
        String customerId,
        String customerName,
        BigDecimal totalBalance,
        List<AccountSummaryResponse> accounts,
        long transactionsThisMonth,
        BigDecimal totalDebitThisMonth,
        BigDecimal totalCreditThisMonth
) {
}