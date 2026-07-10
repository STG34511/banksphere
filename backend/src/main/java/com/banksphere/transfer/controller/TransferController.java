package com.banksphere.transfer.controller;

import com.banksphere.common.dto.ApiResponse;
import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.dto.response.TransferResponse;
import com.banksphere.transfer.service.TransferWorkflowService;
import com.banksphere.transfer.service.system.TransferSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferWorkflowService transferWorkflowService;
    private final TransferSystemService transferSystemService;

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<TransferResponse>> performTransfer(@RequestBody TransferRequest transferRequest) {
        TransferResponse response = transferWorkflowService.transfer(transferRequest);
        return new ResponseEntity<>(new ApiResponse<>(true, "Request Successful", response), HttpStatus.CREATED);
    }


    @PostMapping("/recharge")
    public ResponseEntity<ApiResponse<TransferResponse>> rechargeAccount(@RequestParam String accountNumber, @RequestParam BigDecimal amount, @RequestParam String idempotencyKey) {
        TransferResponse response = transferSystemService.recharge(accountNumber, amount, idempotencyKey);
        return new ResponseEntity<>(new ApiResponse<>(true, "Request Successful", response), HttpStatus.CREATED);

    }

}
