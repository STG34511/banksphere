package com.banksphere.onboarding.dto.response;

import com.banksphere.onboarding.enums.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationDetailsResponse(String applicationReference, String firstName, String lastName, String email, String mobileNumber, String panNumber, String aadharNumber, ApplicationStatus status, LocalDateTime submittedAt) {
}
