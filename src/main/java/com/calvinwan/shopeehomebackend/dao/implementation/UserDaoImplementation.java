package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.user.UserDto;
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
        String sql = "INSERT INTO myuser (id, email, password, name, phone_number,avatar, is_deleted) VALUES (:id, :email, :password, :name, :phoneNumber, :avatar, :isDeleted)";
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString());
        map.put("email", userDto.getEmail());
        map.put("password", userDto.getPassword());
        map.put("name", userDto.getName());
        map.put("phoneNumber", userDto.getPhoneNumber());
        map.put("avatar", userDto.getAvatar());
        map.put("isDeleted", userDto.isDeleted());
        jdbcTemplate.update(sql, map);

        List<String> addresses = userDto.getAddresses();
        String sqlAddress = "INSERT INTO user_address (user_id, address) VALUES (:id, :address)";
        for (String address : addresses) {
            Map<String, Object> mapAddress = new HashMap<>();
            mapAddress.put("id", map.get("id"));
            mapAddress.put("address", address);
            jdbcTemplate.update(sqlAddress, mapAddress);
        }

        return (String) map.get("id");
    }

    @Override
    public User getById(String id) {
        String sql = "SELECT id, email, password, name, phone_number,avatar, is_deleted FROM myuser WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<User> users = jdbcTemplate.query(sql, map, new UserRowMapper());
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);

        String sqlAddress = "SELECT address FROM user_address WHERE user_id = :id";
        List<String> addresses = jdbcTemplate.queryForList(sqlAddress, map, String.class);
        user.setAddresses(addresses);

        return user;
    }

    @Override
    public User getByEmail(String email) {
        String sql = "SELECT id, email, password, name, phone_number,avatar, is_deleted FROM myuser WHERE email = :email";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<User> users = jdbcTemplate.query(sql, map, new UserRowMapper());
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);

        String sqlAddress = "SELECT address FROM user_address WHERE user_id = :id";
        map.put("id", user.getId());
        List<String> addresses = jdbcTemplate.queryForList(sqlAddress, map, String.class);
        user.setAddresses(addresses);

        return user;
    }

    @Override
    public void updateById(String id, UserDto userDto) {
        String sql = "UPDATE myuser SET email = :email, password = :password, name = :name, phone_number = :phoneNumber,avatar = :avatar, is_deleted = :isDeleted WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("email", userDto.getEmail());
        map.put("password", userDto.getPassword());
        map.put("name", userDto.getName());
        map.put("phoneNumber", userDto.getPhoneNumber());
        map.put("avatar", userDto.getAvatar());
        map.put("isDeleted", userDto.isDeleted());
        jdbcTemplate.update(sql, map);

        List<String> addresses = userDto.getAddresses();

        String sqlDeleteAddress = "DELETE FROM user_address WHERE user_id = :id";
        jdbcTemplate.update(sqlDeleteAddress, map);

        for (String address : addresses) {
            String sqlAddress = "INSERT INTO user_address (user_id, address) VALUES (:id, :address)";
            Map<String, Object> mapAddress = new HashMap<>();
            mapAddress.put("id", id);
            mapAddress.put("address", address);
            jdbcTemplate.update(sqlAddress, mapAddress);
        }
    }

    @Override
    public void updateAvatarById(String id, String avatar) {
        String sql = "UPDATE myuser SET avatar = :avatar WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("avatar", avatar);
        jdbcTemplate.update(sql, map);
    }


}
