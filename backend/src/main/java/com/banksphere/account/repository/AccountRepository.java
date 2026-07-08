package com.banksphere.account.repository;

import com.banksphere.account.entity.Account;
import com.banksphere.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByCustomerAndPrimaryAccountTrue(Customer customer);
}
