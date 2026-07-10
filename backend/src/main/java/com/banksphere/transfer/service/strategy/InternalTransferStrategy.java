package com.banksphere.transfer.service.strategy;

import com.banksphere.account.entity.Account;
import com.banksphere.account.repository.AccountRepository;
import com.banksphere.accounting.dto.AccountingInstruction;
import com.banksphere.accounting.dto.PostingInstruction;
import com.banksphere.accounting.enums.EntryType;
import com.banksphere.accounting.journal.enums.JournalType;
import com.banksphere.accounting.service.AccountingService;
import com.banksphere.common.service.ReferenceGenerationService;
import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.dto.response.TransferResponse;
import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.entity.enums.TransferMode;
import com.banksphere.transfer.entity.enums.TransferStatus;
import com.banksphere.transfer.entity.enums.TransferType;
import com.banksphere.transfer.repository.TransferRepository;
import com.banksphere.transfer.service.TransferValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class InternalTransferStrategy implements PaymentRailStrategy {

    private final AccountRepository accountRepository;
    private final TransferValidationService transferValidationService;
    private final AccountingService accountingService;
    private final ReferenceGenerationService referenceGenerationService;
    private final TransferRepository transferRepository;

    @Override
    public TransferMode getSupportedMode() {
        return TransferMode.INTERNAL;
    }

    @Override
    @Transactional
    public TransferResponse process(TransferRequest request) {
        return executeWithRetry(() -> processInternalTransfer(request));
    }

    private TransferResponse processInternalTransfer(TransferRequest request) {
        Account sourceAccount = accountRepository.findById(request.sourceAccountId()).orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        Account destinationAccount = accountRepository.findById(request.destinationAccountId()).orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        transferValidationService.validateInternalTransfer(sourceAccount, destinationAccount, request);

        Transfer transfer = createTransfer(sourceAccount, destinationAccount, request);

        transferRepository.save(transfer);

        AccountingInstruction instruction = buildAccountingInstruction(sourceAccount, destinationAccount, transfer);

        accountingService.post(instruction);

        markSuccess(transfer);

        return buildResponse(transfer);
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

    private TransferResponse buildResponse(Transfer transfer) {
        return new TransferResponse(transfer.getReferenceNumber(), transfer.getStatus(), transfer.getAmount(), transfer.getTransferMode(), transfer.getCreatedAt(), transfer.getCompletedAt(), transfer.getRemarks());
    }

    private void markSuccess(Transfer transfer) {
        transfer.setStatus(TransferStatus.SUCCESS);
        transfer.setCompletedAt(LocalDateTime.now());
    }

    private AccountingInstruction buildAccountingInstruction(Account sourceAccount, Account destinationAccount, Transfer transfer) {
        List<PostingInstruction> instructions = new ArrayList<>();
        instructions.add(debit(sourceAccount, transfer.getAmount(), "Transfer to " + destinationAccount.getAccountNumber()));
        instructions.add(credit(destinationAccount, transfer.getAmount(), "Transfer from " + sourceAccount.getAccountNumber()));

        return new AccountingInstruction(transfer, JournalType.TRANSFER, "Internal Transfer", instructions);
    }

    private Transfer createTransfer(Account sourceAccount, Account destinationAccount, TransferRequest request) {
        Transfer transfer = new Transfer();
        transfer.setReferenceNumber(referenceGenerationService.generateTransferReference());
        transfer.setSourceAccount(sourceAccount);
        transfer.setDestinationAccount(destinationAccount);
        transfer.setBeneficiaryAccountNumber(destinationAccount.getAccountNumber());
        transfer.setTransferMode(request.transferMode());
        transfer.setAmount(request.amount());
        transfer.setIdempotencyKey(request.idempotencyKey());
        transfer.setTransferType(TransferType.CUSTOMER_TRANSFER);
        transfer.setFeeAmount(new BigDecimal(0));
        transfer.setRemarks(request.remarks());
        transfer.setStatus(TransferStatus.INITIATED);
        transfer.setBeneficiaryName(destinationAccount.getCustomer().getFullName());
        transfer.setBeneficiaryIfsc(destinationAccount.getIfsc());
        transfer.setRetryCount(0);
        return transfer;

    }


    private <T> T executeWithRetry(Supplier<T> supplier) {

        for (int attempt = 1; ; attempt++) {

            try {
                return supplier.get();
            } catch (ObjectOptimisticLockingFailureException ex) {

                if (attempt == 3) {
                    throw ex;
                }

                System.out.println("Retrying transfer due to optimistic lock. Attempt :" + attempt);
            }
        }

    }
}
