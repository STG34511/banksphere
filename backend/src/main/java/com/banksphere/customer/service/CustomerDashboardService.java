package com.banksphere.customer.service;

import com.banksphere.customer.dto.response.DashboardResponse;
import com.banksphere.customer.dto.response.TransactionSummaryResponse;
import com.banksphere.customer.enums.TransactionDirection;
import com.banksphere.transfer.entity.enums.TransferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;

public interface CustomerDashboardService {
    DashboardResponse getCustomerDashboard(Authentication authentication);

    Page<TransactionSummaryResponse> getTransactions(Authentication authentication, TransactionDirection direction, TransferStatus status, LocalDate fromDate, LocalDate toDate, Pageable pageable);
}
