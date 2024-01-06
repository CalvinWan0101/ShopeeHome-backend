package com.calvinwan.shopeehomebackend.mapper;

import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeasoningCouponRowMapper implements RowMapper<SeasoningCoupon> {
    @Override
    public SeasoningCoupon mapRow(ResultSet rs, int rowNum) throws SQLException {
        SeasoningCoupon seasoningCoupon = new SeasoningCoupon(
                rs.getString("id"),
                rs.getDate("date"),
                rs.getString("shop_id"),
                rs.getBoolean("is_deleted"),
                rs.getDouble("rate")
        );
        return seasoningCoupon;
    }
}
