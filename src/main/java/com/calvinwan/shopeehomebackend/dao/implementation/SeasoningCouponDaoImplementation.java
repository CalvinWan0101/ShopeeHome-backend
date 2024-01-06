package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.SeasoningCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponUserDto;
import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SeasoningCouponDaoImplementation implements SeasoningCouponDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public String shopCreateCoupon(SeasoningCouponDto seasoningCouponDto) {
        String sql = "INSERT INTO coupon (id, date, shop_id, is_deleted) VALUES (:id, :date, :shopId, :isDeleted)";
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString());
        map.put("date", seasoningCouponDto.getDate());
        map.put("shopId", seasoningCouponDto.getShopId());
        map.put("isDeleted", seasoningCouponDto.isDeleted());

        jdbcTemplate.update(sql, map);

        sql = "INSERT INTO seasoning_coupon (coupon_id, rate) VALUES (:couponId, :rate)";
        Map<String, Object> couponMap = new HashMap<>();
        couponMap.put("couponId", map.get("id"));
        couponMap.put("rate", seasoningCouponDto.getRate());

        jdbcTemplate.update(sql, couponMap);

        return (String) map.get("id");
    }

    @Override
    public String shopUpdateCouponById(String id, SeasoningCouponDto seasoningCouponDto) {
        String sql = "UPDATE coupon SET date = :date, shop_id = :shopId, is_deleted = :isDeleted WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("date", seasoningCouponDto.getDate());
        map.put("shopId", seasoningCouponDto.getShopId());
        map.put("isDeleted", seasoningCouponDto.isDeleted());

        jdbcTemplate.update(sql, map);

        sql = "UPDATE seasoning_coupon SET rate = :rate WHERE coupon_id = :couponId";
        Map<String, Object> couponMap = new HashMap<>();
        couponMap.put("couponId", id);
        couponMap.put("rate", seasoningCouponDto.getRate());

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
    public SeasoningCoupon getById(String id) {
        String sql = "SELECT c.id, c.date, c.shop_id, c.is_deleted, s.rate" +
                " FROM coupon c" +
                " JOIN seasoning_coupon s ON c.id = s.coupon_id" +
                " WHERE c.id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<SeasoningCoupon> seasoningCoupons = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            SeasoningCoupon seasoningCoupon = new SeasoningCoupon(
                    rs.getString("id"),
                    rs.getDate("date"),
                    rs.getString("shop_id"),
                    rs.getBoolean("is_deleted"),
                    rs.getDouble("rate"));
            return seasoningCoupon;
        });
        if (seasoningCoupons.isEmpty()) {
            return null;
        }
        return seasoningCoupons.get(0);
    }

    @Override
    public List<SeasoningCoupon> shopGetByShopId(String shopId) {
        String sql = "SELECT c.id, c.date, c.shop_id, c.is_deleted, s.rate" +
                " FROM coupon c" +
                " JOIN seasoning_coupon s ON c.id = s.coupon_id" +
                " WHERE c.shop_id = :shopId AND c.is_deleted = false";
        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);

        List<SeasoningCoupon> seasoningCoupons = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            SeasoningCoupon seasoningCoupon = new SeasoningCoupon(
                    rs.getString("id"),
                    rs.getDate("date"),
                    rs.getString("shop_id"),
                    rs.getBoolean("is_deleted"),
                    rs.getDouble("rate"));
            return seasoningCoupon;
        });
        return seasoningCoupons;
    }

    @Override
    public List<SeasoningCouponUserDto> userGetByShopId(String userId, String shopId) {
        String sql = "SELECT c.id, c.date, c.shop_id, c.is_deleted, s.rate," +
                "CASE WHEN uc.coupon_id IS NOT NULL THEN true ELSE false END AS is_claimed, uc.is_used " +
                "FROM coupon c " +
                "JOIN seasoning_coupon s ON c.id = s.coupon_id " +
                "LEFT JOIN user_has_coupon uc ON c.id = uc.coupon_id AND uc.user_id = :userId " +
                "WHERE c.shop_id = :shopId AND c.is_deleted = false AND c.date >= CURRENT_DATE" +
                " ORDER BY s.rate ASC";
        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);
        map.put("userId", userId);

        List<SeasoningCouponUserDto> seasoningCouponUserDtos = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            SeasoningCouponUserDto seasoningCouponUserDto = new SeasoningCouponUserDto(
                    rs.getString("id"),
                    rs.getDate("date"),
                    rs.getString("shop_id"),
                    rs.getDouble("rate"),
                    rs.getBoolean("is_claimed"),
                    rs.getBoolean("is_used"),
                    rs.getBoolean("is_deleted"));
            return seasoningCouponUserDto;
        });
        return seasoningCouponUserDtos;
    }

    @Override
    public void deleteById(String id) {
        String sql = "UPDATE coupon SET is_deleted = TRUE WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        jdbcTemplate.update(sql, map);
    }
}
