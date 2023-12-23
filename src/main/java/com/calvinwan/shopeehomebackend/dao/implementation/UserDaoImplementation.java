package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.UserDto;
import com.calvinwan.shopeehomebackend.mapper.UserRowMapper;
import com.calvinwan.shopeehomebackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class UserDaoImplementation implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public String insert(UserDto userDto) {
        String sql = "INSERT INTO myuser (id, name, email, phone_number, password) VALUES (:id, :name, :email, :phoneNumber, :password)";
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString());
        map.put("name", userDto.getName());
        map.put("email", userDto.getEmail());
        map.put("phoneNumber", userDto.getPhoneNumber());
        map.put("password", userDto.getPassword());
        jdbcTemplate.update(sql, map);

        return (String) map.get("id");
    }

    @Override
    public User getById(String id) {
        String sql = "SELECT id, name, email, phone_number, password FROM myuser WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<User> users = jdbcTemplate.query(sql, map, new UserRowMapper());
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getByEmail(String email) {
        String sql = "SELECT id, name, email, phone_number, password FROM myuser WHERE email = :email";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<User> users = jdbcTemplate.query(sql, map, new UserRowMapper());
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void updateById(String userId, UserDto userDto) {
        String sql = "UPDATE myuser SET name = :name, email = :email, phone_number = :phoneNumber, password = :password WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        map.put("name", userDto.getName());
        map.put("email", userDto.getEmail());
        map.put("phoneNumber", userDto.getPhoneNumber());
        map.put("password", userDto.getPassword());
        jdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteById(String userId) {
        String sql = "DELETE FROM myuser WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        jdbcTemplate.update(sql, map);
    }
}
