package com.calvinwan.shopeehomebackend.mapper;

import com.calvinwan.shopeehomebackend.model.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin = new Admin(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("password")
        );
        return admin;
    }
}
