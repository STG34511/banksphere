package com.banksphere.accounting.ledger.repository;

import com.banksphere.accounting.ledger.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LedgerRepository extends JpaRepository<LedgerEntry, UUID> {
}
