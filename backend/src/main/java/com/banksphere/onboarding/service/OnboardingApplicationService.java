package com.banksphere.onboarding.service;

import com.banksphere.onboarding.dto.request.CreateApplicationRequest;
import com.banksphere.onboarding.dto.response.ApplicationResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

public interface OnboardingApplicationService {
    ApplicationResponse submitOnboardingApplication(@Valid CreateApplicationRequest applicationRequest);
}
