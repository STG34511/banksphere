package com.banksphere.onboarding.mapper;

import com.banksphere.onboarding.dto.request.CreateApplicationRequest;
import com.banksphere.onboarding.dto.response.ApplicationDetailsResponse;
import com.banksphere.onboarding.dto.response.ApplicationResponse;
import com.banksphere.onboarding.dto.response.ApplicationSummary;
import com.banksphere.onboarding.entity.OnboardingApplication;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OnboardingApplicationMapper {

    public OnboardingApplication toEntity(
            CreateApplicationRequest request) {

        OnboardingApplication application =
                new OnboardingApplication();

        application.setFirstName(request.firstName());
        application.setLastName(request.lastName());
        application.setEmail(request.email());
        application.setMobileNumber(request.mobileNumber());
        application.setPanNumber(request.panNumber());
        application.setAadhaarNumber(request.aadhaarNumber());
        application.setDateOfBirth(request.dateOfBirth());

        application.setAddressLine1(request.addressLine1());
        application.setAddressLine2(request.addressLine2());
        application.setCity(request.city());
        application.setState(request.state());
        application.setPostalCode(request.postalCode());

        return application;
    }

    public ApplicationResponse toResponse(
            OnboardingApplication application) {

        return new ApplicationResponse(
                application.getApplicationReference(),
                application.getStatus(),
                application.getSubmittedAt()
        );
    }

    public Page<ApplicationSummary> toApplicationSummaryPage(Page<OnboardingApplication> applications) {
        return applications.map(application ->
                new ApplicationSummary(
                        application.getApplicationReference(),
                        application.getFirstName() + " " + application.getLastName(),
                        application.getEmail(),
                        application.getStatus(),
                        application.getSubmittedAt()
                )
        );
    }

    public ApplicationSummary toApplicationSummary(OnboardingApplication application) {
        return new ApplicationSummary(application.getApplicationReference(),
                application.getFirstName() + " " + application.getLastName(),
                application.getEmail(),
                application.getStatus(),
                application.getSubmittedAt());
    }

    public ApplicationDetailsResponse toApplicationDetails(OnboardingApplication application) {
        return new ApplicationDetailsResponse(application.getApplicationReference(),application.getFirstName(),application.getLastName(),application.getEmail(),application.getMobileNumber(),application.getPanNumber(),application.getAadhaarNumber(),application.getStatus(),application.getSubmittedAt());
    }
}