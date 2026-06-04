# BankSphere - Product Requirements

## Overview

BankSphere is a digital banking platform designed to simulate realistic banking operations while
demonstrating enterprise-grade backend architecture and transaction processing.

The project focuses on customer onboarding, account management, beneficiary management, ledger-
based accounting, regulatory screening, transaction processing, refunds, auditability, and operational
workflows.

## Actors

### Customer

Can:

```
Register for a banking account
Login
View account information
View balance
View transaction history
Manage beneficiaries
Initiate transfers
View transaction status
Raise transaction enquiries
```
### Officer

Can:

```
Review customer applications
Approve customer onboarding
Reject customer onboarding
Review flagged transactions
Approve flagged transactions
Reject flagged transactions
Freeze accounts
Close accounts
```
### Admin

Can:

```
Manage officers
Configure system parameters
View reports
View audit trails
```
#### • • • • • • • • • • • • • • • • • • • • •


## Account Lifecycle

#### PENDING_APPROVAL

#### ACTIVE

#### FROZEN

#### CLOSED

## Beneficiaries

Maximum 5 active beneficiaries.

Beneficiaries contain:

```
Nickname
Account Number
IFSC
```
Beneficiaries are convenience shortcuts and do not require approval.

## Transfer Types

### Internal Transfer

Transfer between BankSphere accounts.

### External Transfer

Transfer to another bank using:

#### IMPS

#### NEFT

#### RTGS

## Regulatory Checks

Every transaction undergoes:

```
Balance validation
Daily transfer limit validation
Account status validation
OFAC screening
```
#### • • • • • • • • • •


Transactions failing OFAC checks enter manual review.

## Transaction States

#### INITIATED

#### PROCESSING

#### PENDING_REVIEW

#### SUCCESS

#### FAILED

#### REFUND_PENDING

#### REFUNDED

## Ledger

Ledger is the source of truth.

Every monetary movement generates immutable ledger entries.

Account balance is maintained as a balance snapshot.

## Notifications

Email notifications for:

```
Account approval
Transfer success
Transfer failure
Refund completion
```
## Audit Trail

The system records:

```
Login events
Registration events
Beneficiary updates
Transfer events
Officer approvals
```
#### • • • • • • • • •


```
Refund processing
Account status changes
```
## Transaction Enquiry

Customers can search transactions using reference numbers and track:

```
Current status
Failure reason
Refund status
Review status
```
#### • • • • • •


