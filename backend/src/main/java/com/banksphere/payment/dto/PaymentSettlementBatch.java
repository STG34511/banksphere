package com.banksphere.payment.dto;

import com.banksphere.transfer.entity.enums.TransferMode;

import java.util.List;

public record PaymentSettlementBatch(String batchReference, TransferMode transferMode,
                                     List<PaymentSettlementInstruction> instructions) {

}
