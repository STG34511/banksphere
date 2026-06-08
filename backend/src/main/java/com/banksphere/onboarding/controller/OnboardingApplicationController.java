package com.banksphere.onboarding.controller;

import com.banksphere.common.dto.ApiResponse;
import com.banksphere.onboarding.dto.request.CreateApplicationRequest;
import com.banksphere.onboarding.dto.response.ApplicationResponse;
import com.banksphere.onboarding.entity.OnboardingApplication;
import com.banksphere.onboarding.service.OnboardingApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OnboardingApplicationController {

    private final OnboardingApplicationService onboardingApplicationService;

    @PostMapping("/api/applications")
    public ResponseEntity<ApiResponse<ApplicationResponse>> submitOnboardingApplication(@RequestBody @Valid CreateApplicationRequest applicationRequest) {


        ApplicationResponse response =  onboardingApplicationService.submitOnboardingApplication(applicationRequest);
        return new ResponseEntity<>(new ApiResponse<>(true,"Application submitted successfully", response),HttpStatus.OK);
    }
}
