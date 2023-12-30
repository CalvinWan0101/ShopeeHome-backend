package com.calvinwan.shopeehomebackend.mapper;

import com.calvinwan.shopeehomebackend.model.Shop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopRowMapper implements RowMapper<Shop> {
    @Override
    public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shop shop = new Shop(
                rs.getString("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("phone_number"),
                rs.getString("address"),
                rs.getString("description"),
                rs.getString("avatar"),
                rs.getString("background"),
                rs.getString("creater_id"),
                rs.getString("deleter_id"),
                rs.getBoolean("is_deleted")
        );
        return shop;
    }
}
