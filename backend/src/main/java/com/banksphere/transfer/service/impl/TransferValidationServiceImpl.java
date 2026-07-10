package com.banksphere.transfer.service.impl;

import com.banksphere.account.entity.Account;
import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.service.TransferValidationService;
import org.springframework.stereotype.Service;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {
    @Override
    public void validateInternalTransfer(Account sourceAccount, Account destinationAccount, TransferRequest request) {

    }
}
