package com.banksphere.account.service;

import com.banksphere.account.entity.Account;

public interface AccountService {

    public Account findAccountByAccountNumber(String accountNumber);
}
