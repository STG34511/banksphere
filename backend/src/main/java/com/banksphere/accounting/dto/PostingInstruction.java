package com.banksphere.accounting.dto;

import com.banksphere.account.entity.Account;
import com.banksphere.accounting.enums.EntryType;

import java.math.BigDecimal;

public record PostingInstruction(Account account, EntryType entryType, BigDecimal amount,
                                 String narration) {

}