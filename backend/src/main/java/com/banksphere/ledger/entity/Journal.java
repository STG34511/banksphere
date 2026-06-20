package com.banksphere.ledger.entity;

import com.banksphere.ledger.enums.JournalType;
import com.banksphere.transfer.entity.Transfer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "journal",
        indexes = {
                @Index(
                        name = "idx_journal_transfer",
                        columnList = "transfer_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            nullable = false,
            unique = true
    )
    private String journalNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "transfer_id",
            nullable = false
    )
    private Transfer transfer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JournalType journalType;

    private String description;

    @OneToMany(
            mappedBy = "journal",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<LedgerEntry> ledgerEntries =
            new ArrayList<>();
}