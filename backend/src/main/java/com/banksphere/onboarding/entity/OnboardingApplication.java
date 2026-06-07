package com.banksphere.onboarding.entity;

import com.banksphere.onboarding.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "onboarding_applications")
@Getter
@Setter
@NoArgsConstructor
public class OnboardingApplication{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String applicationReference;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String panNumber;

    @Column(nullable = false)
    private String aadhaarNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;;

    private String rejectionReason;

    private UUID reviewedByOfficerId;

    private LocalDateTime reviewedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime submittedAt;
}