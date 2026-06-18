-- =====================================================
-- Sequences
-- =====================================================

CREATE SEQUENCE application_reference_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE customer_number_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE account_number_seq
    START WITH 1
    INCREMENT BY 1;

-- =====================================================
-- Users
-- =====================================================

CREATE TABLE users
(
    id                       UUID PRIMARY KEY,

    username                 VARCHAR(100) NOT NULL UNIQUE,

    password_hash            VARCHAR(255) NOT NULL,

    role                     VARCHAR(20)  NOT NULL,

    active                   BOOLEAN      NOT NULL,

    created_at               TIMESTAMP    NOT NULL,

    password_change_required BOOLEAN      NOT NULL,

    CONSTRAINT users_role_check
        CHECK (role IN ('CUSTOMER', 'OFFICER'))


);

-- =====================================================
-- Customers
-- =====================================================

CREATE TABLE customers
(
    id              UUID PRIMARY KEY,


    user_id         UUID         NOT NULL UNIQUE,

    customer_number VARCHAR(255) NOT NULL UNIQUE,

    first_name      VARCHAR(100) NOT NULL,

    last_name       VARCHAR(100) NOT NULL,

    email           VARCHAR(255) NOT NULL,

    mobile_number   VARCHAR(20)  NOT NULL,

    pan_number      VARCHAR(10)  NOT NULL,

    aadhaar_number  VARCHAR(12)  NOT NULL,

    date_of_birth   DATE         NOT NULL,

    address_line1   VARCHAR(255),

    address_line2   VARCHAR(255),

    city            VARCHAR(255),

    state           VARCHAR(255),

    postal_code     VARCHAR(255),

    created_at      TIMESTAMP    NOT NULL,

    CONSTRAINT fk_customer_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)


);

-- =====================================================
-- Accounts
-- =====================================================

CREATE TABLE accounts
(
    id                UUID PRIMARY KEY,


    customer_id       UUID           NOT NULL,

    account_number    VARCHAR(255)   NOT NULL UNIQUE,

    available_balance NUMERIC(19, 2) NOT NULL,

    status            VARCHAR(255)   NOT NULL,

    created_at        TIMESTAMP      NOT NULL,

    CONSTRAINT fk_account_customer
        FOREIGN KEY (customer_id)
            REFERENCES customers (id),

    CONSTRAINT accounts_status_check
        CHECK (status IN ('ACTIVE', 'FROZEN', 'CLOSED'))


);

-- =====================================================
-- Onboarding Applications
-- =====================================================

CREATE TABLE onboarding_applications
(
    id                     UUID PRIMARY KEY,

    application_reference  VARCHAR(255) NOT NULL UNIQUE,

    first_name             VARCHAR(255) NOT NULL,

    last_name              VARCHAR(255) NOT NULL,

    email                  VARCHAR(255) NOT NULL,

    mobile_number          VARCHAR(255) NOT NULL,

    pan_number             VARCHAR(255) NOT NULL,

    aadhaar_number         VARCHAR(255) NOT NULL,

    date_of_birth          DATE         NOT NULL,

    address_line1          VARCHAR(255),

    address_line2          VARCHAR(255),

    city                   VARCHAR(255),

    state                  VARCHAR(255),

    postal_code            VARCHAR(255),

    status                 VARCHAR(255) NOT NULL,

    rejection_reason       VARCHAR(255),

    reviewed_by_officer_id UUID,

    submitted_at           TIMESTAMP    NOT NULL,

    reviewed_at            TIMESTAMP,

    CONSTRAINT onboarding_status_check
        CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED'))


);
