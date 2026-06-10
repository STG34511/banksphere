package com.banksphere.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SequenceRepository {

    private final JdbcTemplate jdbcTemplate;

    public Long nextCustomerNumber() {
        return jdbcTemplate.queryForObject(
                "SELECT nextval('customer_number_seq')",
                Long.class
        );
    }

    public Long nextAccountNumber() {
        return jdbcTemplate.queryForObject(
                "SELECT nextval('account_number_seq')",
                Long.class
        );
    }

    public Long nextApplicationReference() {
        return jdbcTemplate.queryForObject(
                "SELECT nextval('application_reference_seq')",
                Long.class
        );
    }
}
