package com.banksphere.accounting.ledger.entity;

import com.banksphere.account.entity.Account;
import com.banksphere.accounting.enums.EntryType;
import com.banksphere.accounting.journal.entity.Journal;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "ledger_entry",
        indexes = {
                @Index(
                        name = "idx_ledger_account",
                        columnList = "account_id"
                ),
                @Index(
                        name = "idx_ledger_journal",
                        columnList = "journal_id"
                ),
                @Index(
                        name = "idx_ledger_created_at",
                        columnList = "created_at"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "journal_id",
            nullable = false
    )
    private Journal journal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "account_id",
            nullable = false
    )
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntryType entryType;

    @Column(
            nullable = false,
            precision = 19,
            scale = 2
    )
    private BigDecimal amount;

    private String narration;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
