package com.banksphere.common.repository;

import com.banksphere.common.enums.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SequenceRepository {

    private final JdbcTemplate jdbcTemplate;

    public Long nextValue(Sequence sequence) {
        return jdbcTemplate.queryForObject(
                "SELECT nextval('" + sequence.getSequenceName() + "')",
                Long.class
        );
    }
}
