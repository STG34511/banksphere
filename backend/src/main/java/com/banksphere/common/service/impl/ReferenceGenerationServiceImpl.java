package com.banksphere.common.service.impl;

import com.banksphere.common.enums.Sequence;
import com.banksphere.common.repository.SequenceRepository;
import com.banksphere.common.service.ReferenceGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReferenceGenerationServiceImpl
        implements ReferenceGenerationService {

    private final SequenceRepository sequenceRepository;

    @Override
    public String generateApplicationReference() {
        return String.format(
                "APP%06d",
                sequenceRepository.nextValue(Sequence.APPLICATION_REFERENCE)
        );
    }

    @Override
    public String generateCustomerNumber() {

        return String.format(
                "CUS%06d",
                sequenceRepository.nextValue(Sequence.CUSTOMER_NUMBER)
        );
    }

    @Override
    public String generateAccountNumber() {

        return String.format(
                "BS1%07d",
                sequenceRepository.nextValue(Sequence.ACCOUNT_NUMBER)
        );
    }

    @Override
    public String generateTransferReference() {
        return String.format(
                "TXN1%07d",
                sequenceRepository.nextValue(Sequence.TRANSFER_REFERENCE)
        );
    }

    @Override
    public String generateJournalReference() {
        return String.format(
                "JN1%07d",
                sequenceRepository.nextValue(Sequence.JOURNAL_NUMBER)
        );
    }


}