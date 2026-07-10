package com.banksphere.transfer.service.strategy;

import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.dto.response.TransferResponse;
import com.banksphere.transfer.entity.enums.TransferMode;

public interface PaymentRailStrategy {
    TransferMode getSupportedMode();

    TransferResponse process(TransferRequest request);
}
