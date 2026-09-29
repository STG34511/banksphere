package com.banksphere.accounting.service.impl;

import com.banksphere.account.entity.Account;
import com.banksphere.account.repository.AccountRepository;
import com.banksphere.accounting.dto.AccountingInstruction;
import com.banksphere.accounting.dto.PostingInstruction;
import com.banksphere.accounting.enums.EntryType;
import com.banksphere.accounting.journal.enums.JournalType;
import com.banksphere.accounting.service.AccountingService;
import com.banksphere.accounting.service.PaymentSettlementService;
import com.banksphere.payment.dto.PaymentSettlementInstruction;
import com.banksphere.transfer.entity.Transfer;
import com.banksphere.transfer.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSettlementServiceImpl implements PaymentSettlementService {

    private final TransferRepository transferRepository;
    private final AccountingService accountingService;
    private final AccountRepository accountRepository;

    @Value("${banksphere.system.accounts.settlement}")
    private String systemAccountsSettlement;

    @Transactional
    @Override
    public AccountingInstruction process(PaymentSettlementInstruction instruction) {
        Transfer transfer = transferRepository
                .findByReferenceNumber(instruction.transferReference())
                .orElseThrow();

        AccountingInstruction accountingInstruction =
                buildAccountingInstruction(transfer);

        accountingService.post(accountingInstruction);
        return accountingInstruction;

    }

    private AccountingInstruction buildAccountingInstruction(Transfer transfer) {

        Account parkingAccount = transfer.getDestinationAccount();


        Account settlementAccount = accountRepository.findByAccountNumber(systemAccountsSettlement).orElseThrow();

        List<PostingInstruction> postings = List.of(
                new PostingInstruction(
                        parkingAccount,
                        EntryType.DEBIT,
                        transfer.getAmount(),
                        "NEFT settlement - " + transfer.getReferenceNumber()
                ),
                new PostingInstruction(
                        settlementAccount,
                        EntryType.CREDIT,
                        transfer.getAmount(),
                        "NEFT settlement - " + transfer.getReferenceNumber()
                )
        );

        return new AccountingInstruction(
                transfer,
                JournalType.SETTLEMENT,
                "Payment settlement - " + transfer.getReferenceNumber(),
                postings
        );
    }
}
