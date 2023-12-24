package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.AdminDao;
import com.calvinwan.shopeehomebackend.mapper.AdminRowMapper;
import com.calvinwan.shopeehomebackend.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdminDaoImplementation implements AdminDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Admin getById(String id) {
        String sql = "SELECT id, name, password FROM admin WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<Admin> admins = jdbcTemplate.query(sql, map, new AdminRowMapper());
        if (admins.isEmpty()) {
            return null;
        }
        return admins.get(0);
    }

    @Override
    public Admin getByName(String name) {
        String sql = "SELECT id, name, password FROM admin WHERE name = :name";
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<Admin> admins = jdbcTemplate.query(sql, map, new AdminRowMapper());
        if (admins.isEmpty()) {
            return null;
        }
        return admins.get(0);
    }
}
