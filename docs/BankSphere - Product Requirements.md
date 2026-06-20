# BankSphere - Product Requirements

## Overview

BankSphere is a digital banking platform designed to simulate realistic banking operations while demonstrating
enterprise-grade backend architecture and transaction processing.

The project focuses on customer onboarding, account management, beneficiary management, ledger-based accounting,
regulatory screening, transaction processing, refunds, auditability and operational workflows.

---

# Architecture Style

The application is implemented as a Modular Monolith.

The codebase is organized into business modules with clear boundaries and may be evolved into microservices in the
future.

---

# Actors

## Customer

Can:

* Register for a banking account
* Login
* View account information
* View balance
* View transaction history
* Manage beneficiaries
* Initiate transfers
* View transaction status
* Raise transaction enquiries

## Officer

Can:

* Review customer applications
* Approve customer onboarding
* Reject customer onboarding
* Review flagged transactions
* Approve flagged transactions
* Reject flagged transactions
* Freeze accounts
* Close accounts

## Admin

Can:

* Manage officers
* Configure system parameters
* View reports
* View audit trails

---

# Customer and Account Model

```text
User
  |
  | 1:1
  |
Customer
  |
  | 1:N
  |
Account
```

---

# Account Lifecycle

* PENDING_APPROVAL
* ACTIVE
* FROZEN
* CLOSED

---

# Beneficiaries

Maximum 5 active beneficiaries.

Fields:

* Nickname
* Account Number
* IFSC

Beneficiaries may belong to BankSphere or external banks.

---

# Transfer Types

## Internal Transfer

Characteristics:

* Immediate processing
* Immediate settlement
* Sender balance updated
* Receiver balance updated
* Ledger entries created

## IMPS

Characteristics:

* Immediate processing
* Immediate settlement
* Transaction status tracked

## NEFT / RTGS

Characteristics:

* Customer debited immediately
* Funds parked internally
* Scheduled settlement
* Refund support
* Transaction status tracking

---

# External Transfer Processing

## IMPS

```text
Customer DR
Settlement CR
SUCCESS
```

## NEFT / RTGS

```text
Customer DR
Parking CR
PENDING
```

Scheduler:

```text
Parking DR
Settlement CR
SUCCESS
```

Refund:

```text
Parking DR
Customer CR
REFUNDED
```

---

# Regulatory Checks

Every transaction undergoes:

* Balance Validation
* Daily Transfer Limit Validation
* Account Status Validation
* OFAC Screening

---

# Transaction States

* INITIATED
* PROCESSING
* PENDING_REVIEW
* SUCCESS
* FAILED
* REFUND_PENDING
* REFUNDED

---

# Ledger Principles

The ledger is the source of truth.

Every monetary movement creates immutable ledger entries.

Ledger entries are append-only and are never updated or deleted.

Balances are maintained as balance snapshots for fast retrieval and can always be reconstructed from ledger history.

The system follows double-entry bookkeeping principles.

---

# System Accounts

* PARKING
* SETTLEMENT
* FEE

---

# Idempotency

Transfer APIs support:

```http
Idempotency-Key
```

Purpose:

* Prevent duplicate transfers
* Handle retries safely
* Prevent accidental double-click submissions

---

# Concurrency

Money movement operations use pessimistic locking.

---

# Notifications

Email notifications are sent for:

* Account approval
* Transfer success
* Transfer failure
* Refund completion

---

# Audit Trail

The system records:

* Login events
* Registration events
* Beneficiary updates
* Transfer events
* Officer approvals
* Officer rejections
* Refund processing
* Account status changes

---

# Transaction Enquiry

Customers can search transactions using reference numbers and view:

* Current status
* Failure reason
* Refund status
* Review status

---

# Non-Goals

* Loan processing
* Credit cards
* Fixed deposits
* Investments
* UPI integration
* Mobile applications
* Multi-currency accounts
* Real banking network integration
* Third-party payment gateway integration
