package com.banksphere.customer.controller;

import com.banksphere.common.dto.ApiResponse;
import com.banksphere.customer.dto.response.DashboardResponse;
import com.banksphere.customer.dto.response.TransactionSummaryResponse;
import com.banksphere.customer.enums.TransactionDirection;
import com.banksphere.customer.service.CustomerDashboardService;
import com.banksphere.transfer.entity.enums.TransferStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerDashboardController {

    private final CustomerDashboardService dashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<DashboardResponse>> getCustomerDashboard(Authentication authentication) {

        DashboardResponse response = dashboardService.getCustomerDashboard(authentication);

        return new ResponseEntity<>(new ApiResponse<>(true, "Data retrieved successfully", response), HttpStatus.OK);
    }

    @GetMapping("/transactions")
    ResponseEntity<ApiResponse<Page<TransactionSummaryResponse>>> getTransactions(
            Authentication authentication,
            @RequestParam(required = false)
            TransactionDirection direction,
            @RequestParam(required = false)
            TransferStatus status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fromDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate toDate,
            @RequestParam(required = false, defaultValue = "10")
            Integer size,
            @RequestParam(required = false, defaultValue = "0")
            Integer page
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<TransactionSummaryResponse> response = dashboardService.getTransactions(authentication, direction, status, fromDate, toDate, pageable);
        return new ResponseEntity<>(new ApiResponse<>(true, "Data retrieved successfully", response), HttpStatus.OK);
    }

}
