package com.banksphere.transfer.service.system;

import com.banksphere.account.entity.Account;
import com.banksphere.account.repository.AccountRepository;
import com.banksphere.transfer.dto.request.BeneficiaryDetails;
import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.dto.response.TransferResponse;
import com.banksphere.transfer.entity.enums.TransferMode;
import com.banksphere.transfer.service.TransferWorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferSystemService {

    private final AccountRepository accountRepository;
    private final TransferWorkflowService transferWorkflowService;

    @Value("${banksphere.system.accounts.funding}")
    private String systemAccountNumber;

    public TransferResponse recharge(String accountNumber, BigDecimal amount, String idempotencyKey) {

        Account sourceAccount = accountRepository.findByAccountNumber(systemAccountNumber).orElseThrow(() -> new IllegalArgumentException("System account not found"));

        Account destinationAccount = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new IllegalArgumentException("No account with that account Id"));

        TransferRequest request = new TransferRequest(sourceAccount.getAccountNumber(), amount, "Recharge on account", idempotencyKey, TransferMode.INTERNAL, new BeneficiaryDetails(destinationAccount.getAccountNumber(), destinationAccount.getCustomer().getFullName(), destinationAccount.getIfsc()));
        return transferWorkflowService.transfer(request);
    }
}
