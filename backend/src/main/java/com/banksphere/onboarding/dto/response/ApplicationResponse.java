package com.banksphere.onboarding.dto.response;

import com.banksphere.onboarding.enums.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationResponse(

        String applicationReference,

        ApplicationStatus status,

        LocalDateTime submittedAt

) {
}