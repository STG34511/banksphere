package com.banksphere.customer.repository;

import com.banksphere.auth.entity.User;
import com.banksphere.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Boolean existsByPanNumber(String panNumber);

    Boolean existsByAadhaarNumber(String aadhaarNumber);

    Optional<Customer> findByUser(User user);
}
