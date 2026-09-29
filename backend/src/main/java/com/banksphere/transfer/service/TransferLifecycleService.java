package com.banksphere.transfer.service;

import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferLifecycleService {

    public void markProcessing(Transfer transfer) {
        transfer.setStatus(TransferStatus.PROCESSING);
    }

    public void markSuccess(Transfer transfer) {
        transfer.setStatus(TransferStatus.SUCCESS);
        transfer.setCompletedAt(LocalDateTime.now());
    }

    public void markPicked(Transfer transfer) {
        transfer.setStatus(TransferStatus.PICKED);
    }

    public void markFailed(Transfer transfer) {
        transfer.setStatus(TransferStatus.FAILED);
        transfer.setCompletedAt(LocalDateTime.now());
    }

    public void markRefundPending(Transfer transfer) {
        transfer.setStatus(TransferStatus.REFUND_PENDING);
    }

    public void markRefunded(Transfer transfer) {
        transfer.setStatus(TransferStatus.REFUNDED);
        transfer.setCompletedAt(LocalDateTime.now());
    }
}
