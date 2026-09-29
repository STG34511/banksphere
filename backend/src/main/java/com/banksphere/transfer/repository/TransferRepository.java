package com.banksphere.transfer.repository;

import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferMode;
import com.banksphere.transfer.entity.enums.TransferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID>, JpaSpecificationExecutor<Transfer> {

    Page<Transfer> findByTransferModeAndStatus(TransferMode transferMode, TransferStatus transferStatus, Pageable pageable);

    Optional<Transfer> findByReferenceNumber(String s);
}
