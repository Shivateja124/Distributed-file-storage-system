package com.shiva.DDFSS.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepo {

    private final JdbcTemplate jdbc;

    public TestRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public String testdb(){
        String sql="SELECT NOW()";
        return jdbc.queryForObject(sql, String.class);
    }
}
