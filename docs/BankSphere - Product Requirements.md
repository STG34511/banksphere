# BankSphere - Product Requirements

## Overview

BankSphere is a digital banking platform designed to simulate realistic banking operations while demonstrating enterprise-grade backend architecture and transaction processing.

The project focuses on customer onboarding, account management, beneficiary management, ledger-based accounting, regulatory screening, transaction processing, refunds, auditability, and operational workflows.

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

Each customer owns a customer profile and may own one or more bank accounts.

During onboarding, the system automatically creates a single account for the customer.

Support for additional account creation may be introduced in future iterations.

Relationship Model:

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

Accounts can exist in the following states:

* PENDING_APPROVAL
* ACTIVE
* FROZEN
* CLOSED

Account status is validated before any monetary transaction is processed.

---

# Beneficiaries

Maximum 5 active beneficiaries per customer.

Beneficiaries contain:

* Nickname
* Account Number
* IFSC

Beneficiaries are convenience shortcuts and do not require approval.

Beneficiaries may belong either to BankSphere accounts or external banks.

---

# Transfer Types

## Internal Transfer

Transfer between two active BankSphere accounts.

Characteristics:

* Immediate processing
* Sender balance updated immediately
* Receiver balance updated immediately
* Ledger entries created for both accounts

## External Transfer

Transfer to another bank using:

* IMPS
* NEFT
* RTGS

Characteristics:

* Sender balance updated immediately
* Transaction status tracked internally
* External bank processing is simulated
* Beneficiary account is not maintained by BankSphere

---

# Regulatory Checks

Every transaction undergoes the following validations:

* Balance validation
* Daily transfer limit validation
* Account status validation
* OFAC screening

Transactions failing validation are rejected.

Transactions flagged by OFAC screening enter a manual review workflow.

---

# Transaction States

Transactions move through the following lifecycle:

* INITIATED
* PROCESSING
* PENDING_REVIEW
* SUCCESS
* FAILED
* REFUND_PENDING
* REFUNDED

State transitions are tracked and auditable.

---

# Ledger

## Ledger Principles

The ledger is the source of truth.

Every monetary movement generates immutable ledger entries.

Account balances are maintained as balance snapshots for fast retrieval.

Balances may always be reconstructed from ledger history.

Ledger entries are never modified after creation.

---

# Notifications

Email notifications are sent for:

* Account approval
* Transfer success
* Transfer failure
* Refund completion

Additional notification types may be introduced in future iterations.

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

Audit records are immutable and available for operational review.

---

# Transaction Enquiry

Customers can search transactions using reference numbers and view:

* Current status
* Failure reason
* Refund status
* Review status

Transaction enquiry serves as the primary customer-facing mechanism for transaction tracking.

---

# Non-Goals

The following capabilities are intentionally excluded from the current project scope:

* Loan processing
* Credit cards
* Fixed deposits
* Investments
* UPI integration
* Mobile banking applications
* Multi-currency accounts
* Real banking network integration
* Third-party payment gateway integration

These capabilities may be explored in future versions but are not required for the current project.

---

# Project Objectives

The project is intended to demonstrate:

* Spring Boot 4
* Spring Security
* PostgreSQL
* Flyway
* React
* TypeScript
* Docker
* Authentication and Authorization
* Banking Workflows
* Transaction Processing
* Regulatory Compliance Flows
* Background Job Processing
* Enterprise Application Architecture

The final deliverable should be suitable for portfolio presentation, technical interviews, and resume discussion.
