package com.banksphere.common.service.impl;

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
                sequenceRepository.nextApplicationReference()
        );
    }

    @Override
    public String generateCustomerNumber() {

        return String.format(
                "CUS%06d",
                sequenceRepository.nextCustomerNumber()
        );
    }

    @Override
    public String generateAccountNumber() {

        return String.format(
                "BS1%07d",
                sequenceRepository.nextAccountNumber()
        );
    }

}