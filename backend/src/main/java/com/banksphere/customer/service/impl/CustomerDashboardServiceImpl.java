package com.banksphere.customer.service.impl;

import com.banksphere.account.entity.Account;
import com.banksphere.auth.entity.User;
import com.banksphere.auth.repository.UserRepository;
import com.banksphere.customer.dto.response.DashboardResponse;
import com.banksphere.customer.entity.Customer;
import com.banksphere.customer.mapper.DashboardMapper;
import com.banksphere.customer.repository.CustomerRepository;
import com.banksphere.customer.service.CustomerDashboardService;
import com.banksphere.transfer.entity.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerDashboardServiceImpl implements CustomerDashboardService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final DashboardMapper mapper;

    @Override
    public DashboardResponse getCustomerDashboard(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        String userName = Objects.requireNonNull(authentication.getPrincipal()).toString();
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Customer customer = customerRepository.findByUser(user).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        List<Account> accounts = customer.getAccounts();
        List<Transfer> debitTransfers = new ArrayList<>();
        List<Transfer> creditTransfers = new ArrayList<>();
        return mapper.mapDashBoardResponse(customer, accounts, debitTransfers, creditTransfers);

    }
}
