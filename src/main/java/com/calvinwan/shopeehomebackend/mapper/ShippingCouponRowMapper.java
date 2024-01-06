package com.calvinwan.shopeehomebackend.mapper;

import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShippingCouponRowMapper implements RowMapper<ShippingCoupon> {
    @Override
    public ShippingCoupon mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShippingCoupon shippingCoupon = new ShippingCoupon(
                rs.getString("id"),
                rs.getDate("date"),
                rs.getString("shop_id"),
                rs.getBoolean("is_deleted"),
                rs.getInt("shipping_limit")
        );
        return shippingCoupon;
    }
}
