package com.banksphere.accounting.service;

import com.banksphere.accounting.dto.AccountingInstruction;
import com.banksphere.payment.dto.PaymentSettlementInstruction;

public interface PaymentSettlementService {

    AccountingInstruction process(PaymentSettlementInstruction instruction);

}
