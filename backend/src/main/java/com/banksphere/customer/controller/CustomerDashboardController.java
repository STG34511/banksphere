package com.banksphere.customer.controller;

import com.banksphere.customer.dto.response.DashboardResponse;
import com.banksphere.customer.service.CustomerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerDashboardController {

    private final CustomerDashboardService dashboardService;

    @GetMapping
    public ResponseEntity<?> getCustomerDashboard(Authentication authentication) {

        DashboardResponse response = dashboardService.getCustomerDashboard(authentication);

        return ResponseEntity.ok().build();
    }

}
