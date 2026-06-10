package com.banksphere.onboarding.service.impl;

import com.banksphere.account.entity.Account;
import com.banksphere.account.enums.AccountStatus;
import com.banksphere.account.repository.AccountRepository;
import com.banksphere.auth.entity.User;
import com.banksphere.auth.enums.Role;
import com.banksphere.auth.repository.UserRepository;
import com.banksphere.common.exception.ApplicationOnboardingException;
import com.banksphere.common.exception.ResourceNotFoundException;
import com.banksphere.common.service.ReferenceGenerationService;
import com.banksphere.customer.entity.Customer;
import com.banksphere.customer.repository.CustomerRepository;
import com.banksphere.onboarding.dto.response.ApplicationDetailsResponse;
import com.banksphere.onboarding.dto.response.ApplicationSummary;
import com.banksphere.onboarding.dto.response.ApprovalResponse;
import com.banksphere.onboarding.entity.OnboardingApplication;
import com.banksphere.onboarding.enums.ApplicationStatus;
import com.banksphere.onboarding.mapper.OnboardingApplicationMapper;
import com.banksphere.onboarding.repository.OnboardingApplicationRepository;
import com.banksphere.onboarding.service.OfficerApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OfficerApplicationServiceImpl implements OfficerApplicationService {

   private final OnboardingApplicationRepository onboardingApplicationRepository;
   private final OnboardingApplicationMapper mapper;
   private final ReferenceGenerationService referenceGenerationService;
   private final UserRepository userRepository;
   private final CustomerRepository customerRepository;
   private final AccountRepository accountRepository;

    @Override
    public Page<ApplicationSummary> findPendingApplications(ApplicationStatus status, Pageable pageable) {
        Page<OnboardingApplication> applications = onboardingApplicationRepository.findAllByStatus(status, pageable);
        return mapper.toApplicationSummaryPage(applications);
    }

    @Override
    public ApplicationDetailsResponse getApplicationDetails(String reference) {
        OnboardingApplication application = onboardingApplicationRepository.findByApplicationReference(reference).orElseThrow(() -> new ResourceNotFoundException("No Application Found with Reference " + reference));
        return mapper.toApplicationDetails(application);
    }

    @Override
    public ApplicationSummary rejectApplication(String reference, String reason) {
        OnboardingApplication application = onboardingApplicationRepository.findByApplicationReference(reference).orElseThrow(() -> new ResourceNotFoundException("No Application Found with Reference " + reference));
        application.setStatus(ApplicationStatus.REJECTED);
        application.setReviewedAt(LocalDateTime.now());
        application.setRejectionReason(reason);
        application.setReviewedByOfficerId(
                UUID.fromString(
                        "11111111-1111-1111-1111-111111111111"
                )
        );
        onboardingApplicationRepository.save(application);
        return mapper.toApplicationSummary(application);
    }

    @Transactional
    @Override
    public ApprovalResponse approveApplication(String reference) {
        OnboardingApplication application =
                onboardingApplicationRepository
                        .findByApplicationReference(reference)
                        .orElseThrow(() -> new ResourceNotFoundException("No Application Found with Reference " + reference));
        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new ApplicationOnboardingException(
                    "Only pending applications can be approved"
            );
        }

        String customerNumber = referenceGenerationService.generateCustomerNumber();

        User user = createNewUser(customerNumber);

        Customer customer = createNewCustomer(application, user,customerNumber);

        Account account = createNewAccount(customer);

        markApplicationAsApproved(application);

        return new ApprovalResponse(application.getApplicationReference(),customer.getCustomerNumber(),account.getAccountNumber());
    }

    private void markApplicationAsApproved(OnboardingApplication application) {
        application.setStatus(ApplicationStatus.APPROVED);
        application.setReviewedAt(LocalDateTime.now());
        application.setReviewedByOfficerId( UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        ));
        onboardingApplicationRepository.save(application);
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setAccountNumber(referenceGenerationService.generateAccountNumber());
        account.setCustomer( customer );
        account.setStatus(AccountStatus.ACTIVE);
        account.setAvailableBalance(new BigDecimal(0));
        account = accountRepository.save(account);
        customer.getAccounts().add(account);
        customerRepository.save(customer);
        return account;
    }

    private Customer createNewCustomer(OnboardingApplication application, User user,String customerNumber) {
        Customer customer = new Customer();
        customer.setCustomerNumber(customerNumber);
        customer.setUser(user);
        customer.setCity(application.getCity());
        customer.setAadhaarNumber(application.getAadhaarNumber());
        customer.setEmail(application.getEmail());
        customer.setFirstName(application.getFirstName());
        customer.setLastName(application.getLastName());
        customer.setPanNumber(application.getPanNumber());
        customer.setAddressLine1(application.getAddressLine1());
        customer.setAddressLine2(application.getAddressLine2());
        customer.setPostalCode(application.getPostalCode());
        customer.setDateOfBirth(application.getDateOfBirth());
        customer.setMobileNumber(application.getMobileNumber());
        customer.setState(application.getState());
        return customerRepository.save(customer);
    }

    private User createNewUser(String customerNumber) {
        User user = new User();
        user.setUsername(customerNumber);
        user.setRole(Role.CUSTOMER);
        user.setActive(true);
        user.setPasswordHash("welcome@123");
        return userRepository.save(user);
    }
}
