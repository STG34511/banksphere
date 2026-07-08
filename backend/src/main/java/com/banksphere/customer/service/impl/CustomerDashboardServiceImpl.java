package com.banksphere.customer.service.impl;

import com.banksphere.account.entity.Account;
import com.banksphere.account.repository.AccountRepository;
import com.banksphere.auth.entity.User;
import com.banksphere.auth.repository.UserRepository;
import com.banksphere.customer.dto.response.DashboardResponse;
import com.banksphere.customer.dto.response.TransactionSummaryResponse;
import com.banksphere.customer.entity.Customer;
import com.banksphere.customer.enums.TransactionDirection;
import com.banksphere.customer.mapper.DashboardMapper;
import com.banksphere.customer.repository.CustomerRepository;
import com.banksphere.customer.service.CustomerDashboardService;
import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferStatus;
import com.banksphere.transfer.repository.TransferRepository;
import com.banksphere.transfer.specifications.TransferSpecifications;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerDashboardServiceImpl implements CustomerDashboardService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final DashboardMapper mapper;
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    @Override
    public DashboardResponse getCustomerDashboard(@NonNull Authentication authentication) {
        String userName = ((UserDetails) Objects.requireNonNull(authentication.getPrincipal())).getUsername();
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Customer customer = customerRepository.findByUser(user).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        List<Account> accounts = customer.getAccounts();
        List<Transfer> debitTransfers = new ArrayList<>();
        List<Transfer> creditTransfers = new ArrayList<>();
        return mapper.mapDashBoardResponse(customer, accounts, debitTransfers, creditTransfers);

    }

    @Override
    public Page<TransactionSummaryResponse> getTransactions(Authentication authentication, TransactionDirection direction, TransferStatus status, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        String userName = ((UserDetails) Objects.requireNonNull(authentication.getPrincipal())).getUsername();
        User currentUser = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        Customer customer = customerRepository.findByUser(currentUser).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        Account account = accountRepository.findByCustomerAndPrimaryAccountTrue(customer).orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        UUID accountId = account.getId();
        Specification<Transfer> spec = Specification.where(TransferSpecifications.byAccount(accountId)).and(TransferSpecifications.byDirection(accountId, direction)).and(TransferSpecifications.byStatus(status)).and(TransferSpecifications.byDateRange(fromDate, toDate));
        Page<Transfer> transfers = transferRepository.findAll(spec, pageable);
        return transfers.map(transfer -> mapper.mapToTransactionSummary(transfer, accountId));

    }

}
