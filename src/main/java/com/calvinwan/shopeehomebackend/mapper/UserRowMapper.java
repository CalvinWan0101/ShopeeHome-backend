package com.calvinwan.shopeehomebackend.mapper;

import com.calvinwan.shopeehomebackend.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(
                rs.getString("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("phone_number"),
                rs.getString("avatar"),
                rs.getBoolean("is_deleted")
        );
        return user;
    }
}
