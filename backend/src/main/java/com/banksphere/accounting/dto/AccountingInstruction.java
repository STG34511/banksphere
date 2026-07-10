package com.banksphere.accounting.dto;

import com.banksphere.accounting.journal.enums.JournalType;
import com.banksphere.transfer.entity.Transfer;

import java.util.List;

public record AccountingInstruction(Transfer transfer, JournalType journalType, String description,
                                    List<PostingInstruction> postings) {
}
