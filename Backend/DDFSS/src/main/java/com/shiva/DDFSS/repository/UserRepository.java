package com.shiva.DDFSS.repository;

import com.shiva.DDFSS.model.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public Users findByName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        List<Users> users = jdbc.query(sql, new Object[]{username}, (rs, rowNum) -> {
            Users user = new Users();
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        });

        return users.isEmpty() ? null : users.get(0);
    }

    public Users findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        List<Users> users = jdbc.query(sql, new Object[]{email}, (rs, rowNum) -> {
            Users user = new Users();
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        });

        return users.isEmpty() ? null : users.get(0);
    }

    public int register(Users user){
        String sql="insert into users(email,username,password) values(?,?,?)";
        return jdbc.update(sql,user.getEmail(),user.getUsername(),user.getPassword());
    }

}
