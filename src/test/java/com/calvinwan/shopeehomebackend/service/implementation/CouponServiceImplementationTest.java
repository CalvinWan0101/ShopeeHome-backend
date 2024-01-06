package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dto.coupon.CouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.CouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponDto;
import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;
import com.calvinwan.shopeehomebackend.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class CouponServiceImplementationTest {
    @Autowired
    private CouponService compoundService;

    @Test
    public void get_seasoning_coupon_by_id() {
        SeasoningCoupon seasoningCoupon = compoundService.getSeasoningCouponById("efbec3f1-563b-4b71-892b-a6db85bf76dc");

        assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCoupon.getId());
        assertEquals(Date.valueOf("2024-03-01"), seasoningCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
        assertFalse(seasoningCoupon.isDeleted());
        assertEquals(0.1, seasoningCoupon.getRate());
    }

    @Test
    public void get_shipping_coupon_by_id() {
        ShippingCoupon shippingCoupon = compoundService.getShippingCouponById("3bfd295f-3215-4585-b935-6e253ad1e54f");

        assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCoupon.getId());
        assertEquals("2024-01-01", shippingCoupon.getDate().toString());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
        assertFalse(shippingCoupon.isDeleted());
        assertEquals(1000, shippingCoupon.getShippingLimit());
    }

    @Test
    public void shop_get_by_shop_id() {
        CouponDto couponDto = compoundService.shopGetByShopId("1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(2, couponDto.getSeasoningCoupons().size());
        assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", couponDto.getSeasoningCoupons().get(0).getId());
        assertEquals("838db05f-07fe-44e4-907f-cf7919cf6777", couponDto.getSeasoningCoupons().get(1).getId());
        assertEquals("ca752e58-0387-4116-9f93-e9043db87b52", couponDto.getShippingCoupons().get(0).getId());
        assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", couponDto.getShippingCoupons().get(1).getId());
        assertEquals("7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca", couponDto.getShippingCoupons().get(2).getId());
    }

    @Test
    public void user_get_by_shop_id() {
        CouponUserDto couponUserDto = compoundService.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", "1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(2, couponUserDto.getSeasoningCouponUserDtos().size());
        assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", couponUserDto.getSeasoningCouponUserDtos().get(0).getId());
        assertFalse(couponUserDto.getSeasoningCouponUserDtos().get(0).isDeleted());
        assertTrue(couponUserDto.getSeasoningCouponUserDtos().get(0).isClaimed());
        assertFalse(couponUserDto.getSeasoningCouponUserDtos().get(0).isUsed());
        assertEquals("838db05f-07fe-44e4-907f-cf7919cf6777", couponUserDto.getSeasoningCouponUserDtos().get(1).getId());
        assertFalse(couponUserDto.getSeasoningCouponUserDtos().get(1).isDeleted());
        assertFalse(couponUserDto.getSeasoningCouponUserDtos().get(1).isClaimed());
        assertFalse(couponUserDto.getSeasoningCouponUserDtos().get(1).isUsed());
        assertEquals(3, couponUserDto.getShippingCouponUserDtos().size());
        assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", couponUserDto.getShippingCouponUserDtos().get(0).getId());
        assertFalse(couponUserDto.getShippingCouponUserDtos().get(0).isDeleted());
        assertTrue(couponUserDto.getShippingCouponUserDtos().get(0).isClaimed());
        assertFalse(couponUserDto.getShippingCouponUserDtos().get(0).isUsed());
        assertEquals("7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca", couponUserDto.getShippingCouponUserDtos().get(1).getId());
        assertFalse(couponUserDto.getShippingCouponUserDtos().get(1).isDeleted());
        assertTrue(couponUserDto.getShippingCouponUserDtos().get(1).isClaimed());
        assertFalse(couponUserDto.getShippingCouponUserDtos().get(1).isUsed());
        assertEquals("ca752e58-0387-4116-9f93-e9043db87b52", couponUserDto.getShippingCouponUserDtos().get(2).getId());
        assertFalse(couponUserDto.getShippingCouponUserDtos().get(2).isDeleted());
        assertFalse(couponUserDto.getShippingCouponUserDtos().get(2).isClaimed());
        assertFalse(couponUserDto.getShippingCouponUserDtos().get(2).isUsed());
    }

    @Test
    @Transactional
    public void create_seasoning_coupon() {
        SeasoningCouponDto seasoningCouponDto = new SeasoningCouponDto(
                Date.valueOf("2021-01-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                0.98,
                false
        );

        String id = compoundService.createSeasoningCoupon(seasoningCouponDto);
        SeasoningCoupon seasoningCoupon = compoundService.getSeasoningCouponById(id);

        assertEquals(id, seasoningCoupon.getId());
        assertEquals(Date.valueOf("2021-01-01"), seasoningCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
        assertFalse(seasoningCoupon.isDeleted());
        assertEquals(0.98, seasoningCoupon.getRate());
    }

    @Test
    @Transactional
    public void create_shipping_coupon() {
        ShippingCouponDto shippingCouponDto = new ShippingCouponDto(
                Date.valueOf("2024-01-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                1000,
                false
        );

        String id = compoundService.createShippingCoupon(shippingCouponDto);
        ShippingCoupon shippingCoupon = compoundService.getShippingCouponById(id);

        assertEquals(id, shippingCoupon.getId());
        assertEquals(Date.valueOf("2024-01-01"), shippingCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
        assertFalse(shippingCoupon.isDeleted());
        assertEquals(1000, shippingCoupon.getShippingLimit());
    }

    @Test
    @Transactional
    public void update_seasoning_coupon_by_id() {
        SeasoningCouponDto seasoningCouponDto = new SeasoningCouponDto(
                Date.valueOf("2021-01-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                0.98,
                false
        );

        String id = compoundService.updateSeasoningCouponById("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCouponDto);
        SeasoningCoupon seasoningCoupon = compoundService.getSeasoningCouponById(id);

        assertEquals(id, seasoningCoupon.getId());
        assertEquals(Date.valueOf("2021-01-01"), seasoningCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
        assertFalse(seasoningCoupon.isDeleted());
        assertEquals(0.98, seasoningCoupon.getRate());
    }

    @Test
    @Transactional
    public void update_shipping_coupon_by_id() {
        ShippingCouponDto shippingCouponDto = new ShippingCouponDto(
                Date.valueOf("2024-01-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                1000,
                false
        );

        String id = compoundService.updateShippingCouponById("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCouponDto);
        ShippingCoupon shippingCoupon = compoundService.getShippingCouponById(id);

        assertEquals(id, shippingCoupon.getId());
        assertEquals(Date.valueOf("2024-01-01"), shippingCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
        assertFalse(shippingCoupon.isDeleted());
        assertEquals(1000, shippingCoupon.getShippingLimit());
    }

    @Test
    @Transactional
    public void user_claim_coupon() {
        String id = compoundService.userClaimCoupon("30e7e267-c791-424a-a94b-fa5e695d27e7", "ca752e58-0387-4116-9f93-e9043db87b52");

        CouponUserDto compoundUserDto = compoundService.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", "1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(id, compoundUserDto.getShippingCouponUserDtos().get(2).getId());
        assertFalse(compoundUserDto.getShippingCouponUserDtos().get(2).isDeleted());
        assertTrue(compoundUserDto.getShippingCouponUserDtos().get(2).isClaimed());
        assertFalse(compoundUserDto.getShippingCouponUserDtos().get(2).isUsed());
    }

    @Test
    @Transactional
    public void delete_by_id() {
        compoundService.deleteById("efbec3f1-563b-4b71-892b-a6db85bf76dc");
        SeasoningCoupon seasoningCoupon = compoundService.getSeasoningCouponById("efbec3f1-563b-4b71-892b-a6db85bf76dc");

        assertTrue(seasoningCoupon.isDeleted());
    }
}