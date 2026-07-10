package com.banksphere.transfer.entity.enums;

public enum TransferStatus {

    // Transfer accepted and persisted
    INITIATED,

    // Business validations / accounting in progress
    PROCESSING,

    // Waiting for manual intervention (OFAC, officer approval)
    PENDING_REVIEW,

    // Waiting for NEFT batch submission
    PARKED,

    // Sent to external payment network (NPCI/RBI)
    SENT_TO_NETWORK,

    // Network has not responded yet
    WAITING_NETWORK_CONFIRMATION,

    // Successfully completed
    SUCCESS,

    // Payment failed
    FAILED,

    // Refund scheduler will process this
    REFUND_PENDING,

    // Money credited back to source account
    REFUNDED
}
