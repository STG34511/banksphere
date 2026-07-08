package com.banksphere.transfer.specifications;

import com.banksphere.customer.enums.TransactionDirection;
import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferStatus;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransferSpecifications {

    public static Specification<Transfer> byAccount(UUID accountId) {
        return (root, query, cb) -> cb.or(cb.equal(root.get("sourceAccount").get("id"), accountId), cb.equal(root.get("destinationAccount").get("id"), accountId));
    }

    public static Specification<Transfer> byStatus(TransferStatus status) {
        return (root, query, cb) -> {
            if (status == null) {
                return cb.conjunction();
            }

            return cb.equal(root.get("status"), status);
        };
    }

    public static Specification<Transfer> byDateRange(LocalDate fromDate, LocalDate toDate) {
        return (root, query, cb) -> {

            if (fromDate == null && toDate == null) {
                return cb.conjunction();
            }

            Path<LocalDateTime> createdAt = root.get("createdAt");

            if (fromDate != null && toDate != null) {
                return cb.between(createdAt, fromDate.atStartOfDay(), toDate.plusDays(1).atStartOfDay());
            }

            if (fromDate != null) {
                return cb.greaterThanOrEqualTo(createdAt, fromDate.atStartOfDay());
            }

            return cb.lessThan(createdAt, toDate.plusDays(1).atStartOfDay());
        };
    }

    public static Specification<Transfer> byDirection(UUID accountId, TransactionDirection direction) {
        return (root, query, cb) -> {

            if (direction == null) {
                return cb.conjunction();
            }

            if (direction == TransactionDirection.DEBIT) {
                return cb.equal(root.get("sourceAccount").get("id"), accountId);
            }

            return cb.equal(root.get("destinationAccount").get("id"), accountId);
        };
    }


}
