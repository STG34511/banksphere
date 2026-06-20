package com.banksphere.customer.mapper;

import com.banksphere.account.entity.Account;
import com.banksphere.customer.dto.response.AccountSummaryResponse;
import com.banksphere.customer.dto.response.DashboardResponse;
import com.banksphere.customer.entity.Customer;
import com.banksphere.transfer.entity.Transfer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DashboardMapper {
    public DashboardResponse mapDashBoardResponse(Customer customer, List<Account> accounts, List<Transfer> debitTransfers, List<Transfer> creditTransfers) {
        List<AccountSummaryResponse> accountSummaries = accounts.stream().map(acc -> new AccountSummaryResponse(acc.getAccountNumber(), acc.getAccountType(), acc.getStatus(), acc.getAvailableBalance(), acc.getPrimaryAccount())).toList();
        BigDecimal totalBalance = accounts.stream().map(Account::getAvailableBalance).reduce(new BigDecimal(0), BigDecimal::add);
        long transactionsThisMonth = debitTransfers.size() + creditTransfers.size();
        BigDecimal totalDebitThisMonth = debitTransfers.stream().map(Transfer::getAmount).reduce(new BigDecimal(0), BigDecimal::add);
        BigDecimal totalCreditThisMonth = creditTransfers.stream().map(Transfer::getAmount).reduce(new BigDecimal(0), BigDecimal::add);
        return new DashboardResponse(customer.getCustomerNumber(), String.join(customer.getFirstName(), customer.getLastName(), " "), totalBalance, accountSummaries, transactionsThisMonth, totalDebitThisMonth, totalCreditThisMonth);
    }
}
