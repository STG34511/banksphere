package com.banksphere.transfer.dto.request;

import com.banksphere.transfer.entity.enums.TransferMode;

import java.io.Serializable;
import java.math.BigDecimal;

public record TransferRequest(String sourceAccountNumber, BigDecimal amount,
                              String remarks, String idempotencyKey,
                              TransferMode transferMode,
                              BeneficiaryDetails beneficiaryDetails) implements Serializable {
}
