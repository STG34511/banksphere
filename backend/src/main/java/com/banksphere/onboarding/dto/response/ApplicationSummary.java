package com.banksphere.onboarding.dto.response;

import com.banksphere.onboarding.enums.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationSummary(String applicationReference, String fullName, String email, ApplicationStatus status, LocalDateTime submittedAt) {}