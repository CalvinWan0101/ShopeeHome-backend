package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ShippingCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponUserDto;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class ShippingCouponDaoImplementation implements ShippingCouponDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public String shopCreateCoupon(ShippingCouponDto shippingCouponDto) {
        String sql = "INSERT INTO coupon (id, date, shop_id, is_deleted) VALUES (:id, :date, :shopId, :isDeleted)";
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString());
        map.put("date", shippingCouponDto.getDate());
        map.put("shopId", shippingCouponDto.getShopId());
        map.put("isDeleted", shippingCouponDto.isDeleted());

        jdbcTemplate.update(sql, map);

        sql = "INSERT INTO shipping_coupon (coupon_id, shipping_limit) VALUES (:couponId, :shippingLimit)";
        Map<String, Object> couponMap = new HashMap<>();
        couponMap.put("couponId", map.get("id"));
        couponMap.put("shippingLimit", shippingCouponDto.getShippingLimit());

        jdbcTemplate.update(sql, couponMap);

        return (String) map.get("id");
    }

    @Override
    public String shopUpdateCouponById(String id, ShippingCouponDto shippingCouponDto) {
        String sql = "UPDATE coupon SET date = :date, shop_id = :shopId, is_deleted = :isDeleted WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("date", shippingCouponDto.getDate());
        map.put("shopId", shippingCouponDto.getShopId());
        map.put("isDeleted", shippingCouponDto.isDeleted());

        jdbcTemplate.update(sql, map);

        sql = "UPDATE shipping_coupon SET shipping_limit = :shippingLimit WHERE coupon_id = :couponId";
        Map<String, Object> couponMap = new HashMap<>();
        couponMap.put("couponId", id);
        couponMap.put("shippingLimit", shippingCouponDto.getShippingLimit());

        jdbcTemplate.update(sql, couponMap);

        return id;
    }

    @Override
    public String userClaimCoupon(String userId, String couponId) {
        String sql = "INSERT INTO user_has_coupon (user_id, coupon_id, is_used) VALUES (:userId, :couponId, :isUsed)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("couponId", couponId);
        map.put("isUsed", false);

        jdbcTemplate.update(sql, map);

        return couponId;
    }

    @Override
    public ShippingCoupon getById(String id) {
        String sql = "SELECT c.id, c.date, c.shop_id, c.is_deleted, sc.shipping_limit " +
                "FROM coupon c " +
                "JOIN shipping_coupon sc ON c.id = sc.coupon_id " +
                "WHERE c.id = :id";


        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<ShippingCoupon> shippingCoupons = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            ShippingCoupon shippingCoupon = new ShippingCoupon(
                    rs.getString("id"),
                    rs.getDate("date"),
                    rs.getString("shop_id"),
                    rs.getBoolean("is_deleted"),
                    rs.getInt("shipping_limit"));
            return shippingCoupon;
        });
        if (shippingCoupons.isEmpty()) {
            return null;
        }
        return shippingCoupons.get(0);
    }

    @Override
    public List<ShippingCoupon> shopGetByShopId(String shopId) {
        String sql = "SELECT c.id, c.date, c.shop_id, c.is_deleted, sc.shipping_limit " +
                "FROM coupon c " +
                "JOIN shipping_coupon sc ON c.id = sc.coupon_id " +
                "WHERE c.shop_id = :shopId" +
                " ORDER BY sc.shipping_limit DESC";
        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);

        List<ShippingCoupon> shippingCoupons = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            ShippingCoupon shippingCoupon = new ShippingCoupon(
                    rs.getString("id"),
                    rs.getDate("date"),
                    rs.getString("shop_id"),
                    rs.getBoolean("is_deleted"),
                    rs.getInt("shipping_limit"));
            return shippingCoupon;
        });
        return shippingCoupons;
    }

    @Override
    public List<ShippingCouponUserDto> userGetByShopId(String userId, String shopId) {
        String sql = "SELECT c.id, c.date, c.shop_id, c.is_deleted, s.shipping_limit," +
                " CASE WHEN uc.coupon_id IS NOT NULL THEN true ELSE false END AS is_claimed, uc.is_used" +
                " FROM coupon c" +
                " JOIN shipping_coupon s ON c.id = s.coupon_id" +
                " LEFT JOIN user_has_coupon uc ON c.id = uc.coupon_id AND uc.user_id = :userId" +
                " WHERE c.shop_id = :shopId" +
                " ORDER BY s.shipping_limit ASC";
        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);
        map.put("userId", userId);

        List<ShippingCouponUserDto> shippingCouponUserDtos = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            ShippingCouponUserDto shippingCouponUserDto = new ShippingCouponUserDto(
                    rs.getString("id"),
                    rs.getDate("date"),
                    rs.getString("shop_id"),
                    rs.getInt("shipping_limit"),
                    rs.getBoolean("is_claimed"),
                    rs.getBoolean("is_used"),
                    rs.getBoolean("is_deleted"));
            return shippingCouponUserDto;
        });
        return shippingCouponUserDtos;
    }
}
