package com.banksphere.customer.service;

import com.banksphere.customer.dto.response.DashboardResponse;
import org.springframework.security.core.Authentication;

public interface CustomerDashboardService {
    DashboardResponse getCustomerDashboard(Authentication authentication);
}
