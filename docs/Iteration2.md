# Iteration 2 - Core Banking

## Goal

Introduce the first set of real banking capabilities by enabling customers to:

* View account information
* View recent transactions
* Manage beneficiaries
* Perform internal fund transfers
* Establish the ledger foundation for future money movement

This iteration transitions BankSphere from an onboarding application into an actual banking platform.

---

# Objectives

By the end of this iteration, the system should support:

* Customer dashboard APIs
* Account information APIs
* Transaction history APIs
* Beneficiary management
* Ledger infrastructure
* Internal fund transfers
* Idempotent money movement
* Concurrency-safe balance updates

Every feature introduced in this iteration must remain:

* Deployable
* Demonstrable
* Interview-ready

---

# Phase 1 - Dashboard APIs

## Goal

Allow customers to view account information and recent activity.

## Features

### Dashboard Summary API

```http
GET /api/customer/dashboard
```

Returns:

* Customer information
* Primary account information
* Available balance
* Ledger balance
* Last transaction
* Pending transaction count

---

### Customer Accounts API

```http
GET /api/customer/accounts
GET /api/customer/accounts/{id}
```

Returns:

* Account Number
* Account Type
* Account Status
* Available Balance
* Ledger Balance
* IFSC
* Branch

---

### Recent Transactions API

```http
GET /api/customer/transactions/recent
```

Returns:

* Transaction Reference
* Amount
* Direction
* Status
* Created Date

---

## Success Criteria

* Customer can login and view account information.
* Dashboard loads successfully.
* Recent transactions are visible.
* Account information is retrieved securely.

---

# Phase 2 - Beneficiary Management

## Goal

Allow customers to manage saved beneficiaries.

## Features

### Add Beneficiary

```http
POST /api/customer/beneficiaries
```

### List Beneficiaries

```http
GET /api/customer/beneficiaries
```

### Update Beneficiary

```http
PUT /api/customer/beneficiaries/{id}
```

### Delete Beneficiary

```http
DELETE /api/customer/beneficiaries/{id}
```

---

## Business Rules

* Maximum of 5 active beneficiaries.
* Beneficiary account number must be unique per customer.
* Beneficiaries may belong to BankSphere or external banks.
* Deleted beneficiaries are soft deleted.

---

## Success Criteria

* Customer can add beneficiaries.
* Customer can update beneficiaries.
* Customer can remove beneficiaries.
* Maximum beneficiary rule is enforced.

---

# Phase 3 - Ledger Foundation

## Goal

Establish the accounting foundation for money movement.

---

# Ledger Principles

* Ledger is the source of truth.
* Every monetary movement creates immutable ledger entries.
* Ledger entries are append-only.
* Account balances are stored as snapshots for performance.
* Balances can always be reconstructed from ledger history.
* Every journal must satisfy:

```text
Total Debits = Total Credits
```

---

# System Accounts

The system maintains internal accounts:

## PARKING

Used for:

* NEFT
* RTGS
* Scheduled processing
* Refund workflows

## SETTLEMENT

Represents funds that have left BankSphere.

## FEE

Represents transaction charges collected by the bank.

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

Account rows participating in a transfer are locked during processing to prevent:

* Double spending
* Lost updates
* Concurrent balance corruption

---

## Success Criteria

* Ledger infrastructure is implemented.
* System accounts exist.
* Idempotency support exists.
* Concurrency strategy is implemented.

---

# Phase 4 - Internal Transfers

## Goal

Allow customers to transfer funds between BankSphere accounts.

---

# Supported Flow

```text
Debit Sender
↓
Credit Receiver
↓
Create Ledger Entries
↓
Update Balances
↓
SUCCESS
```

---

# Transfer Processing Pipeline

```text
Transfer Request
↓
Validate Accounts
↓
Validate Account Status
↓
Validate Beneficiary
↓
Validate Balance
↓
Validate Daily Limits
↓
Validate Idempotency
↓
Create Transfer
↓
Create Ledger Entries
↓
Update Account Balances
↓
Persist Transaction
↓
SUCCESS
```

---

# Business Rules

* Sender account must be ACTIVE.
* Receiver account must be ACTIVE.
* Sender cannot transfer to self.
* Amount must be greater than zero.
* Transfer amount cannot exceed daily limits.
* Duplicate requests must return the original response.

---

# APIs

## Transfer Money

```http
POST /api/customer/transfers/internal
```

## Transaction History

```http
GET /api/customer/transactions
```

## Transaction Details

```http
GET /api/customer/transactions/{referenceNumber}
```

---

# Success Criteria

* Customer can transfer money between BankSphere accounts.
* Balances update correctly.
* Ledger entries are created.
* Duplicate submissions are prevented.
* Concurrent requests do not corrupt balances.
* Transaction history is available.

---

# Deliverables

## Backend

* Dashboard APIs
* Account APIs
* Transaction APIs
* Beneficiary APIs
* Ledger infrastructure
* System accounts
* Internal transfer APIs
* Idempotency support
* Concurrency handling

---

## Frontend

* Dashboard integration
* Account details page
* Recent transactions widget
* Beneficiary management screens
* Transfer screen
* Transaction history page

---

# Final Outcome

At the end of Iteration 2, BankSphere becomes a functional banking application capable of:

* Customer account management
* Beneficiary management
* Internal money movement
* Ledger-based accounting
* Concurrency-safe transfers
* Idempotent transaction processing

This iteration establishes the foundation required for external transfers, regulatory workflows, and operational
features introduced in future iterations.
