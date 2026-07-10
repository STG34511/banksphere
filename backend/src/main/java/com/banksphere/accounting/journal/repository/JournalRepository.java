package com.banksphere.accounting.journal.repository;

import com.banksphere.accounting.journal.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JournalRepository extends JpaRepository<Journal, UUID> {
}
