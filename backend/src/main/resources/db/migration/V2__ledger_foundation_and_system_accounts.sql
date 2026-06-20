CREATE EXTENSION IF NOT EXISTS pgcrypto;

----------------------------------------------------
-- ACCOUNT CHANGES
----------------------------------------------------

ALTER TABLE accounts
    ALTER COLUMN customer_id DROP NOT NULL;

ALTER TABLE accounts
    ADD COLUMN IF NOT EXISTS primary_account BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE accounts
    ADD COLUMN IF NOT EXISTS account_type VARCHAR(30) NOT NULL DEFAULT 'SAVINGS';

ALTER TABLE accounts
    ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP NOT NULL default CURRENT_TIMESTAMP;

CREATE UNIQUE INDEX IF NOT EXISTS uk_customer_primary_account
    ON accounts (customer_id)
    WHERE primary_account = TRUE;

----------------------------------------------------
-- TRANSFER
----------------------------------------------------

CREATE TABLE transfer
(
    id                         UUID PRIMARY KEY,

    reference_number           VARCHAR(30)    NOT NULL UNIQUE,

    amount                     NUMERIC(19, 2) NOT NULL,

    fee_amount                 NUMERIC(19, 2) NOT NULL DEFAULT 0,

    source_account_id          UUID           NOT NULL,

    destination_account_id     UUID,

    transfer_type              VARCHAR(30)    NOT NULL,

    status                     VARCHAR(30)    NOT NULL,

    beneficiary_name           VARCHAR(100),

    beneficiary_account_number VARCHAR(30),

    beneficiary_ifsc           VARCHAR(20),

    failure_reason             VARCHAR(500),

    idempotency_key            VARCHAR(100) UNIQUE,

    scheduled_execution_time   TIMESTAMP,

    completed_at               TIMESTAMP,

    created_at                 TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at                 TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transfer_source_account
        FOREIGN KEY (source_account_id)
            REFERENCES accounts (id),

    CONSTRAINT fk_transfer_destination_account
        FOREIGN KEY (destination_account_id)
            REFERENCES accounts (id)
);

CREATE INDEX idx_transfer_reference
    ON transfer (reference_number);

CREATE INDEX idx_transfer_status
    ON transfer (status);

CREATE INDEX idx_transfer_created_at
    ON transfer (created_at);

CREATE INDEX idx_transfer_source_account
    ON transfer (source_account_id);

CREATE INDEX idx_transfer_destination_account
    ON transfer (destination_account_id);

CREATE INDEX idx_transfer_source_created
    ON transfer (source_account_id, created_at);

CREATE INDEX idx_transfer_destination_created
    ON transfer (destination_account_id, created_at);

----------------------------------------------------
-- JOURNAL
----------------------------------------------------

CREATE TABLE journal
(
    id             UUID PRIMARY KEY,

    journal_number VARCHAR(30) NOT NULL UNIQUE,

    transfer_id    UUID        NOT NULL,

    journal_type   VARCHAR(30) NOT NULL,

    description    VARCHAR(255),

    created_at     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_journal_transfer
        FOREIGN KEY (transfer_id)
            REFERENCES transfer (id)
);

CREATE INDEX idx_journal_transfer
    ON journal (transfer_id);

----------------------------------------------------
-- LEDGER ENTRY
----------------------------------------------------

CREATE TABLE ledger_entry
(
    id         UUID PRIMARY KEY,

    journal_id UUID           NOT NULL,

    account_id UUID           NOT NULL,

    entry_type VARCHAR(20)    NOT NULL,

    amount     NUMERIC(19, 2) NOT NULL,

    narration  VARCHAR(255),

    created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_ledger_journal
        FOREIGN KEY (journal_id)
            REFERENCES journal (id),

    CONSTRAINT fk_ledger_account
        FOREIGN KEY (account_id)
            REFERENCES accounts (id)
);

CREATE INDEX idx_ledger_journal
    ON ledger_entry (journal_id);

CREATE INDEX idx_ledger_account
    ON ledger_entry (account_id);

CREATE INDEX idx_ledger_created_at
    ON ledger_entry (created_at);

----------------------------------------------------
-- SYSTEM ACCOUNTS
----------------------------------------------------

INSERT INTO accounts (id,
                      account_number,
                      customer_id,
                      available_balance,
                      primary_account,
                      account_type,
                      status,
                      created_at,
                      updated_at)
SELECT gen_random_uuid(),
       'SYS000001',
       NULL,
       0,
       FALSE,
       'PARKING',
       'ACTIVE',
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1
                  FROM accounts
                  WHERE account_number = 'SYS000001');

INSERT INTO accounts (id,
                      account_number,
                      customer_id,
                      available_balance,
                      primary_account,
                      account_type,
                      status,
                      created_at,
                      updated_at)
SELECT gen_random_uuid(),
       'SYS000002',
       NULL,
       0,
       FALSE,
       'SETTLEMENT',
       'ACTIVE',
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1
                  FROM accounts
                  WHERE account_number = 'SYS000002');

INSERT INTO accounts (id,
                      account_number,
                      customer_id,
                      available_balance,
                      primary_account,
                      account_type,
                      status,
                      created_at,
                      updated_at)
SELECT gen_random_uuid(),
       'SYS000003',
       NULL,
       0,
       FALSE,
       'FEE',
       'ACTIVE',
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1
                  FROM accounts
                  WHERE account_number = 'SYS000003');