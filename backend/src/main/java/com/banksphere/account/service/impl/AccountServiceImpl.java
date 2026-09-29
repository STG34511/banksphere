package com.banksphere.account.service.impl;

import com.banksphere.account.entity.Account;
import com.banksphere.account.repository.AccountRepository;
import com.banksphere.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new IllegalArgumentException("Invalid account number : " + accountNumber));
    }
}
