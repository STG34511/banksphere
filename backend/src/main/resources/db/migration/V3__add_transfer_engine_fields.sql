-- ==========================================
-- V3__add_transfer_engine_fields.sql
-- Transfer Engine Enhancements
-- ==========================================

ALTER TABLE transfer
    ADD COLUMN transfer_mode VARCHAR(30) NOT NULL DEFAULT 'INTERNAL';

ALTER TABLE transfer
    ADD COLUMN remarks VARCHAR(250);

ALTER TABLE transfer
    ADD COLUMN network_reference_number VARCHAR(100);

ALTER TABLE transfer
    ADD COLUMN retry_count INTEGER NOT NULL DEFAULT 0;

CREATE INDEX idx_transfer_mode
    ON transfer (transfer_mode);

CREATE INDEX idx_transfer_network_reference
    ON transfer (network_reference_number);
