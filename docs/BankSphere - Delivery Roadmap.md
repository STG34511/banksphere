# BankSphere - Delivery Roadmap

## Vision

Build a modern banking platform focused on customer onboarding, account management, transaction processing, regulatory compliance and operational workflows.

The project will be delivered through incremental iterations.

Every iteration must result in a deployable and demonstrable application.

---

# Iteration 1 - Foundation (MVP)

## Goal

Establish the core onboarding and authentication workflow.

## Features

### Frontend

* Customer registration page
* Customer login page
* Customer dashboard
* Officer dashboard
* Application review screen

### Backend

* Customer onboarding application submission
* Officer application review
* Application approval and rejection
* User creation
* Customer creation
* Account creation
* Authentication
* Basic authorization
* Initial database schema
* Flyway migrations

## Success Criteria

* Customer can submit an onboarding application
* Officer can review applications
* Officer can approve or reject applications
* System creates User, Customer and Account records upon approval
* Customer can login
* Customer can view account details and balance

## Status

In Progress

---

# Iteration 2 - Core Banking

## Goal

Enable customers to manage beneficiaries and perform internal fund transfers.

## Features

### Beneficiaries

* Add beneficiary
* View beneficiaries
* Update beneficiary
* Delete beneficiary
* Maximum 5 beneficiaries per customer

### Transfers

* Internal bank transfers
* Beneficiary validation
* Balance validation
* Ledger updates
* Transaction history

## Success Criteria

* Customer can manage beneficiaries
* Customer can transfer money within the bank
* Sender balance updates correctly
* Receiver balance updates correctly
* Transaction history is visible

## Status

Planned

---

# Iteration 3 - External Transfers

## Goal

Support external bank transfers through multiple payment rails.

## Features

### Transfer Types

* IMPS
* NEFT
* RTGS

### Processing

* Transfer workflow
* Transaction lifecycle management
* Transfer status tracking
* External beneficiary handling

## Success Criteria

* Customer can initiate IMPS, NEFT and RTGS transfers
* System tracks transaction status
* External transfers are recorded correctly
* Customer can view transfer outcomes

## Status

Planned

---

# Iteration 4 - Regulatory Engine

## Goal

Introduce compliance and approval workflows.

## Features

### Validation Engine

* Daily transaction limit checks
* Account status validation
* Beneficiary validation

### OFAC Screening

* OFAC name matching
* Transaction flagging
* Officer review queue
* Manual approval workflow

## Success Criteria

* Transactions exceeding limits are blocked
* Suspicious transactions are flagged
* Officer can review flagged transactions
* Officer can approve or reject flagged transactions

## Status

Planned

---

# Iteration 5 - Refunds and Enquiry

## Goal

Improve operational resilience and transaction visibility.

## Features

### Transaction Enquiry

* Transaction search
* Status enquiry
* Transaction lifecycle visibility

### Refund Processing

* Refund workflow
* Scheduled refund jobs
* Refund notifications
* Balance restoration

## Success Criteria

* Customer can enquire about transactions
* Failed transactions can be refunded
* Refund jobs execute automatically
* Customer receives refund status updates

## Status

Planned

---

# Iteration 6 - Operations and Production Readiness

## Goal

Introduce operational tooling and prepare the project for portfolio presentation.

## Features

### Operations

* Account freeze
* Account closure
* Audit trail
* Administrative dashboards
* Daily transaction reporting

### Production Readiness

* Docker support
* Deployment configuration
* Architecture documentation
* API documentation
* Project screenshots
* Portfolio-ready README

## Success Criteria

* Officers can freeze and close accounts
* Audit events are recorded
* Daily reports are available
* Application can be deployed using Docker
* Documentation is complete
* Project is portfolio-ready

## Status

Planned

---

# Final Deliverable

BankSphere will demonstrate:

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

The final project should be suitable for portfolio presentation, technical interviews and resume discussion.
