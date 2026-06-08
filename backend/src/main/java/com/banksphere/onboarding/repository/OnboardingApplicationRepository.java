package com.banksphere.onboarding.repository;

import com.banksphere.onboarding.entity.OnboardingApplication;
import com.banksphere.onboarding.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OnboardingApplicationRepository extends JpaRepository<OnboardingApplication, UUID> {
    Optional<OnboardingApplication> findByApplicationReference(String applicationReference);
    Page<OnboardingApplication> findByStatus(
            ApplicationStatus status,
            Pageable pageable
    );
    boolean existsByAadhaarNumberAndStatus(@NotBlank @Pattern(regexp = "^[0-9]{12}$") String s, ApplicationStatus applicationStatus);

    boolean existsByPanNumberAndStatus(@NotBlank @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$") String s, ApplicationStatus applicationStatus);
}
