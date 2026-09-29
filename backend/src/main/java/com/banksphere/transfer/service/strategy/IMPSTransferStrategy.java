package com.banksphere.transfer.service.strategy;

import com.banksphere.account.entity.Account;
import com.banksphere.account.service.AccountService;
import com.banksphere.accounting.dto.AccountingInstruction;
import com.banksphere.accounting.dto.PostingInstruction;
import com.banksphere.accounting.enums.EntryType;
import com.banksphere.accounting.journal.enums.JournalType;
import com.banksphere.accounting.service.AccountingService;
import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.dto.response.TransferResponse;
import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferMode;
import com.banksphere.transfer.repository.TransferRepository;
import com.banksphere.transfer.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IMPSTransferStrategy implements PaymentRailStrategy {

    private final TransferExecutionService transferExecutionService;
    private final AccountService accountService;
    private final TransferRepository transferRepository;
    private final TransferValidationService transferValidationService;
    private final TransferFactory transferFactory;
    private final AccountingService accountingService;
    private final TransferLifecycleService transferLifecycleService;
    private final TransferMapper transferMapper;

    @Value("${banksphere.system.accounts.parking}")
    private String parkingAccountNumber;

    @Override
    public TransferMode getSupportedMode() {
        return TransferMode.IMPS;
    }

    @Override
    @Transactional
    public TransferResponse process(TransferRequest request) {
        return transferExecutionService.executeWithRetry(() -> processIMPSTransfer(request));
    }

    private TransferResponse processIMPSTransfer(TransferRequest request) {
        Account sourceAccount = accountService.findAccountByAccountNumber(request.sourceAccountNumber());
        Account parkingAccount = accountService.findAccountByAccountNumber(parkingAccountNumber);

        transferValidationService.validateIMPSTransfer(sourceAccount, request);

        Transfer transfer = transferFactory.createCustomerTransfer(sourceAccount, parkingAccount, request);

        transferRepository.save(transfer);

        AccountingInstruction instruction = buildAccountingInstruction(sourceAccount, parkingAccount, transfer);

        accountingService.post(instruction);

        transferLifecycleService.markProcessing(transfer);

        return transferMapper.toInitiatedResponse(transfer);
    }


    private PostingInstruction debit(
            Account account,
            BigDecimal amount,
            String narration
    ) {
        return new PostingInstruction(
                account,
                EntryType.DEBIT,
                amount,
                narration
        );
    }

    private PostingInstruction credit(
            Account account,
            BigDecimal amount,
            String narration
    ) {
        return new PostingInstruction(
                account,
                EntryType.CREDIT,
                amount,
                narration
        );
    }


    private AccountingInstruction buildAccountingInstruction(Account sourceAccount, Account destinationAccount, Transfer transfer) {
        List<PostingInstruction> instructions = new ArrayList<>();
        instructions.add(debit(sourceAccount, transfer.getAmount(), "IMPS Parking for " + transfer.getReferenceNumber()));
        instructions.add(credit(destinationAccount, transfer.getAmount(), "Transfer from " + sourceAccount.getAccountNumber()));

        return new AccountingInstruction(transfer, JournalType.TRANSFER, "IMPS Transfer - PARKING", instructions);
    }
}
