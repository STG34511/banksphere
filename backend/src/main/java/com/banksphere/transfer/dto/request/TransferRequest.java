package com.banksphere.transfer.dto.request;

import com.banksphere.transfer.entity.enums.TransferMode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(UUID sourceAccountId, UUID destinationAccountId, BigDecimal amount,
                              String remarks, String idempotencyKey,
                              TransferMode transferMode, String beneficiaryAccountNumber, String beneficiaryName,
                              String beneficiaryIfsc) implements Serializable {
}
