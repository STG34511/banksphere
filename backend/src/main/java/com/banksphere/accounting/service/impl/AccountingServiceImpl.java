package com.banksphere.accounting.service.impl;

import com.banksphere.account.entity.Account;
import com.banksphere.accounting.dto.AccountingInstruction;
import com.banksphere.accounting.dto.PostingInstruction;
import com.banksphere.accounting.enums.EntryType;
import com.banksphere.accounting.journal.entity.Journal;
import com.banksphere.accounting.journal.repository.JournalRepository;
import com.banksphere.accounting.ledger.entity.LedgerEntry;
import com.banksphere.accounting.ledger.repository.LedgerRepository;
import com.banksphere.accounting.service.AccountingService;
import com.banksphere.common.service.ReferenceGenerationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountingServiceImpl implements AccountingService {

    private final JournalRepository journalRepository;
    private final ReferenceGenerationService referenceGenerationService;
    private final LedgerRepository ledgerRepository;

    @Transactional
    @Override
    public void post(AccountingInstruction instruction) {

        validateInstruction(instruction);

        Journal journal = createJournal(instruction);

        createLedgerEntries(journal, instruction);

        updateBalances(instruction);
    }

    private void validateInstruction(AccountingInstruction instruction) {

        if (instruction == null) {
            throw new IllegalArgumentException("Accounting instruction cannot be null");
        }

        if (instruction.postings() == null || instruction.postings().isEmpty()) {
            throw new IllegalArgumentException("At least one posting is required");
        }

    }

    private Journal createJournal(AccountingInstruction instruction) {
        Journal journal = Journal.builder()
                .transfer(instruction.transfer())
                .journalType(instruction.journalType())
                .description(instruction.description())
                .journalNumber(referenceGenerationService.generateJournalReference())
                .build();

        return journalRepository.save(journal);
    }

    private void createLedgerEntries(
            Journal journal,
            AccountingInstruction instruction
    ) {

        List<LedgerEntry> entries = instruction.postings()
                .stream()
                .map(posting ->
                        LedgerEntry.builder()
                                .journal(journal)
                                .account(posting.account())
                                .entryType(posting.entryType())
                                .amount(posting.amount())
                                .narration(posting.narration())
                                .build()
                )
                .toList();

        ledgerRepository.saveAll(entries);
    }


    private void updateBalances(AccountingInstruction instruction) {
        for (PostingInstruction posting : instruction.postings()) {

            if (posting.entryType() == EntryType.DEBIT) {
                debit(posting.account(), posting.amount());
            } else {
                credit(posting.account(), posting.amount());
            }
        }
    }

    private void debit(Account account, BigDecimal amount) {
        account.setAvailableBalance(
                account.getAvailableBalance().subtract(amount)
        );
    }

    private void credit(Account account, BigDecimal amount) {
        account.setAvailableBalance(
                account.getAvailableBalance().add(amount)
        );
    }

}
