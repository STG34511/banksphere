package com.banksphere.onboarding.dto.request;

import com.banksphere.onboarding.enums.ReviewDecision;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewApplicationRequest(

        @NotNull
        ReviewDecision decision,

        @Size(max = 500)
        String rejectionReason

) {
}