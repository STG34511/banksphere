package com.banksphere.payment.dto;

import java.math.BigDecimal;

public record PaymentSettlementInstruction(
        String transferReference,
        BigDecimal amount,
        String beneficiaryAccountNumber,
        String beneficiaryName,
        String beneficiaryIfsc
) {
}
