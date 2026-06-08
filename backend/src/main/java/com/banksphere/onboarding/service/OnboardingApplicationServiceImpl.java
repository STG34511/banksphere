package com.banksphere.onboarding.service;

import com.banksphere.common.exception.ApplicationOnboardingException;
import com.banksphere.customer.repository.CustomerRepository;
import com.banksphere.onboarding.dto.request.CreateApplicationRequest;
import com.banksphere.onboarding.dto.response.ApplicationResponse;
import com.banksphere.onboarding.entity.OnboardingApplication;
import com.banksphere.onboarding.enums.ApplicationStatus;
import com.banksphere.onboarding.mapper.OnboardingApplicationMapper;
import com.banksphere.onboarding.repository.OnboardingApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OnboardingApplicationServiceImpl implements OnboardingApplicationService {

    private final OnboardingApplicationRepository onboardingApplicationRepository;
    private final CustomerRepository customerRepository;
    private final OnboardingApplicationMapper mapper;

    @Override
    public ApplicationResponse submitOnboardingApplication(CreateApplicationRequest applicationRequest) {
        validateRequest(applicationRequest);
        OnboardingApplication application = mapper.toEntity(applicationRequest);
        application.setApplicationReference(generateReference());
        application.setStatus(ApplicationStatus.PENDING);
        application = onboardingApplicationRepository.save(application);
        return mapper.toResponse(application);
    }
    private void validateRequest(
            CreateApplicationRequest request) {

        if (customerRepository.existsByPanNumber(
                request.panNumber())) {

            throw new ApplicationOnboardingException(
                    "Customer already exists with PAN"
            );
        }

        if (customerRepository.existsByAadhaarNumber(
                request.aadhaarNumber())) {

            throw new ApplicationOnboardingException(
                    "Customer already exists with Aadhaar"
            );
        }

        if (onboardingApplicationRepository
                .existsByPanNumberAndStatus(
                        request.panNumber(),
                        ApplicationStatus.PENDING)) {

            throw new ApplicationOnboardingException(
                    "Application already under review"
            );
        }

        if (onboardingApplicationRepository
                .existsByAadhaarNumberAndStatus(
                        request.aadhaarNumber(),
                        ApplicationStatus.PENDING)) {

            throw new ApplicationOnboardingException(
                    "Application already under review"
            );
        }
    }

    private String generateReference() {
        return "APP" + System.currentTimeMillis();
    }
}
