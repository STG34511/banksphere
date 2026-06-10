package com.banksphere.onboarding.service;

import com.banksphere.onboarding.dto.response.ApplicationDetailsResponse;
import com.banksphere.onboarding.dto.response.ApplicationSummary;
import com.banksphere.onboarding.dto.response.ApprovalResponse;
import com.banksphere.onboarding.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OfficerApplicationService {
    Page<ApplicationSummary> findPendingApplications(ApplicationStatus status, Pageable pageable) ;

    ApplicationDetailsResponse getApplicationDetails(String reference);

    ApplicationSummary rejectApplication(String reference, String reason);

    ApprovalResponse approveApplication(String reference);
}
