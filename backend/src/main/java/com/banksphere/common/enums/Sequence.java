package com.banksphere.common.enums;

public enum Sequence {

    CUSTOMER_NUMBER("customer_number_seq"),
    ACCOUNT_NUMBER("account_number_seq"),
    APPLICATION_REFERENCE("application_reference_seq"),
    TRANSFER_REFERENCE("transfer_reference_seq"),
    JOURNAL_NUMBER("journal_number_seq"),
    LEDGER_NUMBER("ledger_number_seq");

    private final String sequenceName;

    Sequence(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getSequenceName() {
        return sequenceName;
    }
}