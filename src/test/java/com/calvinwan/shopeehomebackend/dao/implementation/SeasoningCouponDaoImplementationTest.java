package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.SeasoningCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponUserDto;
import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class SeasoningCouponDaoImplementationTest {

    @Autowired
    private SeasoningCouponDao seasoningCouponDao;

    @Test
    public void get_by_id() {
        SeasoningCoupon seasoningCoupon = seasoningCouponDao.getById("efbec3f1-563b-4b71-892b-a6db85bf76dc");

        assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCoupon.getId());
        assertEquals(Date.valueOf("2024-03-01"), seasoningCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
        assertFalse(seasoningCoupon.isDeleted());
        assertEquals(0.1, seasoningCoupon.getRate());
    }

    @Test
    public void shop_get_by_shop_id() {
        List<SeasoningCoupon> seasoningCoupons = seasoningCouponDao.shopGetByShopId("1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(2, seasoningCoupons.size());
        assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCoupons.get(0).getId());
        assertEquals("838db05f-07fe-44e4-907f-cf7919cf6777", seasoningCoupons.get(1).getId());
    }

    @Test
    public void user_get_by_shop_id() {
        List<SeasoningCouponUserDto> seasoningCouponUserDtos = seasoningCouponDao.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", "1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(2, seasoningCouponUserDtos.size());
        assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCouponUserDtos.get(0).getId());
        assertFalse(seasoningCouponUserDtos.get(0).isDeleted());
        assertTrue(seasoningCouponUserDtos.get(0).isClaimed());
        assertFalse(seasoningCouponUserDtos.get(0).isUsed());
        assertEquals("838db05f-07fe-44e4-907f-cf7919cf6777", seasoningCouponUserDtos.get(1).getId());
        assertFalse(seasoningCouponUserDtos.get(1).isDeleted());
        assertFalse(seasoningCouponUserDtos.get(1).isClaimed());
        assertFalse(seasoningCouponUserDtos.get(1).isUsed());
    }

    @Test
    @Transactional
    public void shop_create_coupon() {
        SeasoningCouponDto seasoningCouponDto = new SeasoningCouponDto(
                Date.valueOf("2021-01-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                0.98,
                false
        );

        String id = seasoningCouponDao.shopCreateCoupon(seasoningCouponDto);
        SeasoningCoupon seasoningCoupon = seasoningCouponDao.getById(id);

        assertEquals(id, seasoningCoupon.getId());
        assertEquals(Date.valueOf("2021-01-01"), seasoningCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
        assertFalse(seasoningCoupon.isDeleted());
        assertEquals(0.98, seasoningCoupon.getRate());
    }

    @Test
    @Transactional
    public void shop_update_coupon_by_id() {
        SeasoningCouponDto seasoningCouponDto = new SeasoningCouponDto(
                Date.valueOf("2021-01-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                0.98,
                false
        );

        String id = seasoningCouponDao.shopUpdateCouponById("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCouponDto);
        SeasoningCoupon seasoningCoupon = seasoningCouponDao.getById(id);

        assertEquals(id, seasoningCoupon.getId());
        assertEquals(Date.valueOf("2021-01-01"), seasoningCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
        assertFalse(seasoningCoupon.isDeleted());
        assertEquals(0.98, seasoningCoupon.getRate());
    }

    @Test
    @Transactional
    public void user_claim_coupon() {
        seasoningCouponDao.userClaimCoupon("30e7e267-c791-424a-a94b-fa5e695d27e7", "ca752e58-0387-4116-9f93-e9043db87b52");
        List<SeasoningCouponUserDto> seasoningCouponUserDtos = seasoningCouponDao.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", "1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(2, seasoningCouponUserDtos.size());
        assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCouponUserDtos.get(0).getId());
        assertFalse(seasoningCouponUserDtos.get(0).isDeleted());
        assertTrue(seasoningCouponUserDtos.get(0).isClaimed());
        assertFalse(seasoningCouponUserDtos.get(0).isUsed());
        assertEquals("838db05f-07fe-44e4-907f-cf7919cf6777", seasoningCouponUserDtos.get(1).getId());
        assertFalse(seasoningCouponUserDtos.get(1).isDeleted());
        assertFalse(seasoningCouponUserDtos.get(1).isClaimed());
        assertFalse(seasoningCouponUserDtos.get(1).isUsed());
    }

    @Test
    @Transactional
    public void delete_by_id() {
        seasoningCouponDao.deleteById("efbec3f1-563b-4b71-892b-a6db85bf76dc");
        SeasoningCoupon seasoningCoupon = seasoningCouponDao.getById("efbec3f1-563b-4b71-892b-a6db85bf76dc");

        assertTrue(seasoningCoupon.isDeleted());
    }
}