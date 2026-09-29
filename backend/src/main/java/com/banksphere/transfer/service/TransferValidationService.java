package com.banksphere.transfer.service;

import com.banksphere.account.entity.Account;
import com.banksphere.transfer.dto.request.TransferRequest;

public interface TransferValidationService {
    void validateInternalTransfer(Account sourceAccount, Account destinationAccount, TransferRequest request);

    void validateNEFTTransfer(Account sourceAccount, TransferRequest request);

    void validateIMPSTransfer(Account sourceAccount, TransferRequest request);

    void validateRTGSTransfer(Account sourceAccount, TransferRequest request);
}
