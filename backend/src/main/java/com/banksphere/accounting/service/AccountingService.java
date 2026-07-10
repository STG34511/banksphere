package com.banksphere.accounting.service;

import com.banksphere.accounting.dto.AccountingInstruction;

public interface AccountingService {
    void post(AccountingInstruction instruction);
}
