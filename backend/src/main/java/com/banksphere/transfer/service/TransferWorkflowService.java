package com.banksphere.transfer.service;

import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.dto.response.TransferResponse;

public interface TransferWorkflowService {
    TransferResponse transfer(TransferRequest transferRequest);

}
