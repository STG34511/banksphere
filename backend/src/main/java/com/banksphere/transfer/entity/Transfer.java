package com.banksphere.transfer.entity;

import com.banksphere.account.entity.Account;
import com.banksphere.ledger.entity.Journal;
import com.banksphere.transfer.entity.enums.TransferStatus;
import com.banksphere.transfer.entity.enums.TransferType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "transfer",
        indexes = {
                @Index(name = "idx_transfer_reference",
                        columnList = "reference_number"),
                @Index(name = "idx_transfer_status",
                        columnList = "status"),
                @Index(name = "idx_transfer_created_at",
                        columnList = "created_at")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            nullable = false,
            unique = true,
            length = 30
    )
    private String referenceNumber;

    @Column(
            nullable = false,
            precision = 19,
            scale = 2
    )
    private BigDecimal amount;

    @Column(
            nullable = false,
            precision = 19,
            scale = 2
    )
    @Builder.Default
    private BigDecimal feeAmount = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "source_account_id",
            nullable = false
    )
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransferType transferType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransferStatus status;

    private String beneficiaryName;

    private String beneficiaryAccountNumber;

    private String beneficiaryIfsc;

    private String failureReason;

    private LocalDateTime scheduledExecutionTime;

    private LocalDateTime completedAt;

    @Column(unique = true)
    private String idempotencyKey;

    @OneToMany(
            mappedBy = "transfer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Journal> journals = new ArrayList<>();
}
