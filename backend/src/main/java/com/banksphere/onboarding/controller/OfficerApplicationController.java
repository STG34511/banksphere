package com.banksphere.onboarding.controller;

import com.banksphere.common.dto.ApiResponse;
import com.banksphere.onboarding.dto.response.ApplicationDetailsResponse;
import com.banksphere.onboarding.dto.response.ApplicationSummary;
import com.banksphere.onboarding.dto.response.ApprovalResponse;
import com.banksphere.onboarding.enums.ApplicationStatus;
import com.banksphere.onboarding.service.OfficerApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class OfficerApplicationController {

    private final OfficerApplicationService applicationService;

    @GetMapping("/api/officer/applications")
    public ResponseEntity<ApiResponse<Page<ApplicationSummary>>> getApplicationsSummary(@RequestParam(required = false , defaultValue ="PENDING") ApplicationStatus status ,  Pageable pageable) {
        Page<ApplicationSummary> response = applicationService.findPendingApplications(status, pageable);
        return new ResponseEntity<>(new ApiResponse<>(true,"Applications retrieved successfully",response), HttpStatus.OK);
    }

    @GetMapping("/api/officer/applications/{reference}")
    public ResponseEntity<ApiResponse<ApplicationDetailsResponse>>  getApplicationDetails(@PathVariable String reference) {
        ApplicationDetailsResponse response = applicationService.getApplicationDetails(reference);
        return new ResponseEntity<>(new ApiResponse<>(true,"Application retrieved successfully",response), HttpStatus.OK);
    }

    @PostMapping("/api/officer/applications/{reference}/reject")
    public ResponseEntity<ApiResponse<ApplicationSummary>> rejectApplication(@PathVariable String reference, @RequestBody String reason) {
        ApplicationSummary response = applicationService.rejectApplication(reference,reason);
        return new ResponseEntity<>(new ApiResponse<>(true,"Application rejected successfully",response), HttpStatus.OK);
    }

    @PostMapping("/api/officer/applications/{reference}/approve")
    public ResponseEntity<ApiResponse<ApprovalResponse>> approveApplication(@PathVariable String reference) {
        ApprovalResponse response = applicationService.approveApplication(reference);
        return new ResponseEntity<>(new ApiResponse<>(true,"Application approved successfully",response), HttpStatus.OK);
    }

}
