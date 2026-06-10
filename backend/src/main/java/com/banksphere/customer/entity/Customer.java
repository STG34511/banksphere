package com.banksphere.customer.entity;

import com.banksphere.account.entity.Account;
import com.banksphere.auth.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(nullable = false, unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(nullable = false, unique = true)
    private String customerNumber;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 20)
    private String mobileNumber;

    @Column(nullable = false, length = 10)
    private String panNumber;

    @Column(nullable = false, length = 12)
    private String aadhaarNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts =  new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}