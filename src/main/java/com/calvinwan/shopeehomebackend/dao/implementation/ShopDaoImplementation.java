package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ShopDao;
import com.calvinwan.shopeehomebackend.dto.shop.ShopDto;
import com.calvinwan.shopeehomebackend.mapper.ShopRowMapper;
import com.calvinwan.shopeehomebackend.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class ShopDaoImplementation implements ShopDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public String insert(ShopDto shopDto) {
        String sql = "INSERT INTO shop (id, email, password, name, phone_number, address, description,avatar, background, creater_id, is_deleted) VALUES(:id, :email, :password, :name, :phoneNumber, :address, :description, :avatar, :background, :createrId, :isDeleted)";
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString());
        map.put("email", shopDto.getEmail());
        map.put("password", shopDto.getPassword());
        map.put("name", shopDto.getName());
        map.put("phoneNumber", shopDto.getPhoneNumber());
        map.put("address", shopDto.getAddress());
        map.put("description", shopDto.getDescription());
        map.put("avatar", shopDto.getAvatar());
        map.put("background", shopDto.getBackground());
        map.put("createrId", shopDto.getCreaterId());
        map.put("isDeleted", shopDto.isDeleted());
        jdbcTemplate.update(sql, map);
        return (String) map.get("id");
    }

    @Override
    public Shop getById(String id) {
        String sql = "SELECT id, email, password, name, phone_number, address, description, avatar, background, creater_id, deleter_id, is_deleted FROM shop WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<Shop> shops = jdbcTemplate.query(sql, map, new ShopRowMapper());
        if (shops.isEmpty()) {
            return null;
        }
        return shops.get(0);
    }

    @Override
    public Shop getByEmail(String email) {
        String sql = "SELECT id, email, password, name, phone_number, address, description, avatar, background, creater_id, deleter_id, is_deleted FROM shop WHERE email = :email";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<Shop> shops = jdbcTemplate.query(sql, map, new ShopRowMapper());

        if (shops.isEmpty()) {
            return null;
        }
        return shops.get(0);
    }

    @Override
    public void updateById(String id, ShopDto shopDto) {
        String sql = "UPDATE shop SET email = :email, password = :password, name = :name, phone_number = :phoneNumber, address = :address, description = :description,avatar = :avatar, background = :background, creater_id = :createrId, deleter_id = :deleterId, is_deleted = :isDeleted WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("email", shopDto.getEmail());
        map.put("password", shopDto.getPassword());
        map.put("name", shopDto.getName());
        map.put("phoneNumber", shopDto.getPhoneNumber());
        map.put("address", shopDto.getAddress());
        map.put("description", shopDto.getDescription());
        map.put("avatar", shopDto.getAvatar());
        map.put("background", shopDto.getBackground());
        map.put("createrId", shopDto.getCreaterId());
        map.put("deleterId", shopDto.getDeleterId());
        map.put("isDeleted", shopDto.isDeleted());
        jdbcTemplate.update(sql, map);
    }

    @Override
    public void updateAvatarAndBackgroundById(String id, String avatar, String background) {
        String sql = "UPDATE shop SET avatar = :avatar, background = :background WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("avatar", avatar);
        map.put("background", background);
        jdbcTemplate.update(sql, map);
    }
}
