-- ==========================================================
-- V4__accounting_engine_foundation.sql
-- Accounting Engine Foundation
-- ==========================================================

--------------------------------------------------------------
-- Optimistic Locking
--------------------------------------------------------------

ALTER TABLE accounts
    ADD COLUMN version BIGINT NOT NULL DEFAULT 0;

ALTER TABLE accounts
    ADD COLUMN ifsc VARCHAR(11) NOT NULL DEFAULT 11111111111;

--------------------------------------------------------------
-- Sequences
--------------------------------------------------------------

CREATE SEQUENCE transfer_reference_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE journal_number_seq
    START WITH 1
    INCREMENT BY 1;


ALTER TABLE accounts
    ADD CONSTRAINT chk_account_ifsc_length
        CHECK (char_length(ifsc) = 11);


INSERT INTO accounts (id,
                      account_number,
                      customer_id,
                      available_balance,
                      primary_account,
                      account_type,
                      status,
                      ifsc,
                      created_at,
                      updated_at)
SELECT gen_random_uuid(),
       'SYS000000',
       NULL,
       999999999999.99,
       FALSE,
       'SYSTEM',
       'ACTIVE',
       'BKSP0000001',
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1
                  FROM accounts
                  WHERE account_number = 'SYS000000');


--------------------------------------------------------------
-- Future additions
--------------------------------------------------------------
-- Keep extending this migration while implementing the
-- accounting engine.
--
-- Planned:
--  - Ledger sequence (if required)
--  - Additional accounting indexes
--  - Constraints
--  - Any schema refinements discovered during implementation
--------------------------------------------------------------