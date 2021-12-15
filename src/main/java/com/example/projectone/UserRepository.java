package com.example.projectone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createUser(String userName, String password) {
        String sql = "INSERT INTO all_users (user_name, password) VALUES (:userName, :password)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", userName);
        paramMap.put("password", password);
        jdbcTemplate.update(sql, paramMap);

    }
}
