# BankSphere

## Overview

BankSphere is a digital banking platform that allows customers to onboard, manage accounts, transfer
funds, manage beneficiaries, and track transactions.

The project focuses on realistic banking transaction processing, regulatory checks, ledger-based
accounting, auditability, and operational workflows rather than basic CRUD functionality.

The application will be implemented as a modular monolith using Spring Boot and React.

# Actors

## Customer

Can:

```
Register
Login
View account details
View balance
View transaction history
Manage beneficiaries
Initiate transfers
View transaction status
Raise transaction enquiries
```
## Officer

Can:

```
Review customer onboarding applications
Approve applications
Reject applications with remarks
Review flagged transactions
Approve or reject flagged transactions
Freeze accounts
Close accounts
```
## Admin

Can:

```
Manage officers
View audit logs
```
### • • • • • • • • • • • • • • • • • •


```
View system reports
Configure transaction limits
```
# Account Lifecycle

### PENDING_APPROVAL

### APPROVED

### ACTIVE

### FROZEN

### CLOSED

# Beneficiaries

Maximum 5 active beneficiaries.

Beneficiaries act as saved receivers and contain:

```
Nickname
Account Number
IFSC
```
Beneficiaries do not require approval.

# Transfer Types

## Internal Transfer

Sender and receiver belong to BankSphere.

Flow:

Debit Sender

Credit Receiver

Ledger Entries Created

Transaction Completed

### •

### •

### •

### •

### •


## External Transfer

Sender belongs to BankSphere.

Receiver belongs to another bank.

Supported rails:

### IMPS

### NEFT

### RTGS

External transfers are simulated.

# Regulatory Checks

Every transaction passes through:

```
Balance Check
Daily Transfer Limit Check
Account Status Check
OFAC Screening
```
OFAC matches result in:

### PENDING_REVIEW

Officer approval required.

# Transaction States

### INITIATED

### PROCESSING

### PENDING_REVIEW

### SUCCESS

### FAILED

### REFUND_PENDING

### REFUNDED

### • • • • • • •


# Ledger

Ledger is the source of truth.

Every transaction creates immutable ledger entries.

Account balance is maintained as a balance snapshot for performance.

# Notifications

Email notifications for:

```
Account Approval
Transfer Success
Transfer Failure
Refund Completion
```
# Audit Trail

Audit entries for:

```
Login
Registration
Beneficiary Changes
Transfer Initiation
Transfer Approval
Refund Processing
Account Freeze
Account Closure
```
# Transaction Enquiry

Customer can search transactions using reference number and view:

```
Current Status
Failure Reason
Refund Status
Review Status
```
### • • • • • • • • • • • • • • • •


# Refund Processing

Failed transactions may enter REFUND_PENDING state.

Scheduler processes pending refunds.

Refund completion creates ledger entries and notifications.


