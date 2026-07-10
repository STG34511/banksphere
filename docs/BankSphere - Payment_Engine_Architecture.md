# BankSphere Payment & Accounting Engine Architecture

**Version:** 1.0  
**Status:** Locked Design (July 2026)

---

# Purpose

This document defines the architecture of the BankSphere payment processing and accounting engine.

The purpose of this document is to establish a fixed architecture before implementation so that future development
remains consistent and scalable.

The accounting engine should support:

- Internal Transfers
- IMPS
- NEFT
- RTGS

without changing the accounting implementation.

---

# Design Principles

## 1. Accounting is independent of payment rails

The accounting engine must never know whether a transaction originated from:

- Internal Transfer
- IMPS
- NEFT
- RTGS
- Scheduler
- Kafka Consumer

Accounting only receives accounting instructions.

---

## 2. Accounting is append-only

Journal entries are immutable.

Ledger entries are immutable.

Transfers are updated only through status changes.

History is never modified.

---

## 3. One service owns accounting

Only one service in the application is allowed to:

- Create Journal Entries
- Create Ledger Entries
- Update Account Balances

That service is:

```
AccountingService
```

No other service may update balances.

---

## 4. Workflow and Accounting are separated

Workflow decides:

- Should money move?
- Which payment rail?
- Which validations?
- Current transfer state?

Accounting decides:

- Debit which account?
- Credit which account?
- Create journals
- Create ledger
- Update balances

---

# High Level Architecture

```
TransferController
        │
        ▼
TransferWorkflowService
        │
        ├──────────────┐
        │              │
        ▼              ▼
ValidationService   FeeService
        │
        ▼
PaymentRailStrategy
        │
        ├─────────────┬──────────────┬──────────────┐
        ▼             ▼              ▼              ▼
 Internal         IMPS          NEFT           RTGS
        │
        ▼
AccountingService
        │
        ├──────────────┬───────────────┬───────────────┐
        ▼              ▼               ▼
Transfer       JournalEntry      LedgerEntry
        │
        ▼
Account Balance
        │
        ▼
Kafka Publisher (when required)
```

---

# Core Services

## TransferWorkflowService

Responsibilities

- Validate request
- Calculate fees
- Select payment rail
- Create Transfer record
- Invoke AccountingService
- Publish Kafka events
- Update Transfer status

This service owns the payment workflow.

---

## ValidationService

Responsible for

- Account status
- Beneficiary validation
- Daily limits
- Available balance
- OFAC (future)
- Idempotency

Validation never updates balances.

---

## FeeService

Responsible only for:

- Calculating applicable fees

FeeService never creates accounting entries.

Fee information is passed to AccountingService.

---

## AccountingService

The single source of truth for financial postings.

Responsibilities

- Create Journal Entries
- Create Ledger Entries
- Update Account balances
- Persist accounting information

AccountingService never knows:

- IMPS
- NEFT
- RTGS
- Kafka
- Scheduler

It only executes accounting instructions.

---

# System Accounts

The following internal accounts are maintained.

---

## Parking Account

Purpose

Money accepted by BankSphere but not yet sent to the payment network.

Used for:

- NEFT batching
- Officer approval
- OFAC review

Ownership

BankSphere

---

## Settlement Account

Purpose

Money already sent to the payment network and awaiting confirmation.

Used for:

- IMPS
- RTGS
- NEFT after batch submission

Ownership

External payment network (NPCI / RBI)

---

## Fee Account

Purpose

Stores all fees collected by the bank.

Never exposed to customers.

---

# Customer Accounts

Customer accounts represent actual customer balances.

Supported account types

- Savings
- Current

Only customer accounts are visible on the frontend.

---

# Accounting Model

Accounting is based on double-entry bookkeeping.

Every balance change produces:

- Transfer
- Journal Entries
- Ledger Entries

Balance updates never occur without accounting records.

---

# Payment Modes

---

# 1. Internal Transfer

Flow

```
Customer

↓

TransferWorkflowService

↓

Validation

↓

Fee Calculation

↓

Accounting

Customer A
      ↓
Customer B

↓

SUCCESS
```

Characteristics

- Fully synchronous
- Single database transaction
- No Kafka
- No Scheduler

---

# 2. IMPS

Flow

```
Customer

↓

Workflow

↓

Accounting

Customer

↓

Settlement

↓

Kafka

↓

PaymentNetworkConsumer

↓

Fake NPCI

↓

SUCCESS / FAILURE / TIMEOUT
```

Success

```
Settlement

↓

Destination Customer

↓

SUCCESS
```

Failure

```
Settlement

↓

Source Customer

↓

FAILED
```

Timeout

```
No accounting movement

Transfer Status

WAITING_NETWORK_CONFIRMATION
```

Characteristics

- Immediate payment
- External coordination simulated using Kafka

---

# 3. NEFT

Flow

```
Customer

↓

Workflow

↓

Accounting

Customer

↓

Parking

↓

PARKED
```

Scheduler

```
Parking

↓

Settlement

↓

Kafka
```

Kafka Consumer

```
Settlement

↓

Destination

↓

SUCCESS
```

Failure

```
Settlement

↓

Parking

↓

FAILED
```

Refund Scheduler

```
Parking

↓

Customer

↓

REFUNDED
```

Characteristics

- Batch based
- Scheduler driven
- Parking account used

---

# 4. RTGS

Flow

```
Customer

↓

Workflow

↓

High Value Validation

↓

Accounting

Customer

↓

Settlement

↓

Kafka

↓

Fake RTGS Network

↓

Settlement

↓

Destination

↓

SUCCESS
```

Characteristics

- High value validation
- Immediate settlement
- External coordination through Kafka

---

# Kafka Consumers

Kafka represents external payment networks.

Consumers never modify balances directly.

Consumers:

- Receive network event
- Simulate external processing
- Build AccountingInstruction
- Invoke AccountingService
- Update Transfer status

---

# Payment Network Simulator

A fake payment network simulates NPCI / RBI.

Supported responses

```
SUCCESS

FAILURE

TIMEOUT
```

The simulator is deterministic for testing.

Example

```
Reference ending in

0

↓

FAILURE

Reference ending in

1

↓

TIMEOUT

Everything else

↓

SUCCESS
```

This allows repeatable demonstrations.

---

# Transfer Lifecycle

```
CREATED

↓

VALIDATING

↓

PROCESSING

↓

PARKED

↓

SENT_TO_NETWORK

↓

WAITING_NETWORK_CONFIRMATION

↓

SUCCESS

↓

FAILED

↓

REFUNDED
```

Not every payment mode uses every state.

---

# Accounting Principles

1. AccountingService is the only service that updates balances.

2. Journal entries are immutable.

3. Ledger entries are immutable.

4. Transfers are the workflow.

5. Accounting records financial events.

6. Workflow controls payment progression.

7. Payment rails never change accounting implementation.

---

# Future Enhancements

Architecture already supports:

- Officer approval
- OFAC screening
- Daily transaction limits
- Notifications
- Audit events
- Reconciliation
- Chargeback/Reversal
- Multiple payment gateways
- Real Kafka infrastructure

without redesigning the accounting engine.

---

# Final Design Philosophy

> **Workflow determines _when_ money should move.**

> **Accounting determines _how_ money is recorded.**

> **Payment rails coordinate external systems.**

> **Accounting remains deterministic, append-only, and independent of transport.**