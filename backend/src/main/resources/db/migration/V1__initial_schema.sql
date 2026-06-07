CREATE TABLE users
(
    id UUID PRIMARY KEY,

    username VARCHAR(100) NOT NULL UNIQUE,

    password_hash VARCHAR(255) NOT NULL,

    role VARCHAR(20) NOT NULL,

    active BOOLEAN NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT users_role_check
        CHECK (
            role IN (
                     'CUSTOMER',
                     'OFFICER'
                )
            )
);

CREATE TABLE customers
(
    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    customer_number VARCHAR(30) NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL,

    mobile_number VARCHAR(20) NOT NULL,

    pan_number VARCHAR(10) NOT NULL,

    aadhaar_number VARCHAR(12) NOT NULL,

    date_of_birth DATE NOT NULL,

    created_at TIMESTAMP NOT NULL
);


CREATE TABLE accounts
(
    id UUID PRIMARY KEY,

    customer_id UUID NOT NULL,

    account_number VARCHAR(30) NOT NULL UNIQUE,

    available_balance NUMERIC(19,2) NOT NULL,

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT accounts_status_check
        CHECK (
            status IN (
                       'ACTIVE',
                       'FROZEN',
                       'CLOSED'
                )
            )
);


CREATE TABLE onboarding_applications
(
    id UUID PRIMARY KEY,

    application_reference VARCHAR(30) NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL,

    mobile_number VARCHAR(20) NOT NULL,

    pan_number VARCHAR(10) NOT NULL,

    aadhaar_number VARCHAR(12) NOT NULL,

    date_of_birth DATE NOT NULL,

    address_line1 VARCHAR(255),

    address_line2 VARCHAR(255),

    city VARCHAR(100),

    state VARCHAR(100),

    postal_code VARCHAR(20),

    status VARCHAR(20) NOT NULL,

    submitted_at TIMESTAMP NOT NULL,

    reviewed_by_officer_id UUID,

    reviewed_at TIMESTAMP,

    rejection_reason VARCHAR(500),

    CONSTRAINT onboarding_applications_status_check
        CHECK (
            status IN (
                       'PENDING',
                       'APPROVED',
                       'REJECTED'
                )
            )
);


