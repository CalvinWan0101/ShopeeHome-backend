package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ShippingCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponUserDto;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;
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
public class ShippingCouponDaoImplementationTest {
    @Autowired
    private ShippingCouponDao shippingCouponDao;

    @Test
    public void get_by_id() {
        ShippingCoupon shippingCoupon = shippingCouponDao.getById("3bfd295f-3215-4585-b935-6e253ad1e54f");

        assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCoupon.getId());
        assertEquals("2024-01-01", shippingCoupon.getDate().toString());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
        assertFalse(shippingCoupon.isDeleted());
        assertEquals(1000, shippingCoupon.getShippingLimit());
    }

    @Test
    public void shop_get_by_shop_id() {
        List<ShippingCoupon> shippingCoupons = shippingCouponDao.shopGetByShopId("1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(3, shippingCoupons.size());
        assertEquals("ca752e58-0387-4116-9f93-e9043db87b52", shippingCoupons.get(0).getId());
        assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCoupons.get(1).getId());
        assertEquals("7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca", shippingCoupons.get(2).getId());
    }

    @Test
    public void user_get_by_shop_id() {
        List<ShippingCouponUserDto> shippingCouponUserDtos = shippingCouponDao.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", "1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(3, shippingCouponUserDtos.size());
        assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCouponUserDtos.get(0).getId());
        assertFalse(shippingCouponUserDtos.get(0).isDeleted());
        assertTrue(shippingCouponUserDtos.get(0).isClaimed());
        assertFalse(shippingCouponUserDtos.get(0).isUsed());
        assertEquals("7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca", shippingCouponUserDtos.get(1).getId());
        assertFalse(shippingCouponUserDtos.get(1).isDeleted());
        assertTrue(shippingCouponUserDtos.get(1).isClaimed());
        assertFalse(shippingCouponUserDtos.get(1).isUsed());
        assertEquals("ca752e58-0387-4116-9f93-e9043db87b52", shippingCouponUserDtos.get(2).getId());
        assertFalse(shippingCouponUserDtos.get(2).isDeleted());
        assertFalse(shippingCouponUserDtos.get(2).isClaimed());
        assertFalse(shippingCouponUserDtos.get(2).isUsed());
    }

    @Test
    @Transactional
    public void shop_create_coupon() {
        ShippingCouponDto shippingCouponDto = new ShippingCouponDto(
                Date.valueOf("2024-01-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                1000,
                false
        );

        String id = shippingCouponDao.shopCreateCoupon(shippingCouponDto);
        ShippingCoupon shippingCoupon = shippingCouponDao.getById(id);

        assertEquals(id, shippingCoupon.getId());
        assertEquals(Date.valueOf("2024-01-01"), shippingCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
        assertFalse(shippingCoupon.isDeleted());
        assertEquals(1000, shippingCoupon.getShippingLimit());
    }

    @Test
    @Transactional
    public void shop_update_coupon_by_id() {
        ShippingCouponDto shippingCouponDto = new ShippingCouponDto(
                Date.valueOf("2024-01-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                1000,
                false
        );

        String id = shippingCouponDao.shopUpdateCouponById("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCouponDto);
        ShippingCoupon shippingCoupon = shippingCouponDao.getById(id);

        assertEquals(id, shippingCoupon.getId());
        assertEquals(Date.valueOf("2024-01-01"), shippingCoupon.getDate());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
        assertFalse(shippingCoupon.isDeleted());
        assertEquals(1000, shippingCoupon.getShippingLimit());
    }

    @Test
    @Transactional
    public void user_claim_coupon() {
        shippingCouponDao.userClaimCoupon("30e7e267-c791-424a-a94b-fa5e695d27e7", "ca752e58-0387-4116-9f93-e9043db87b52");
        List<ShippingCouponUserDto> shippingCouponUserDtos = shippingCouponDao.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", "1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(3, shippingCouponUserDtos.size());
        assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCouponUserDtos.get(0).getId());
        assertFalse(shippingCouponUserDtos.get(0).isDeleted());
        assertTrue(shippingCouponUserDtos.get(0).isClaimed());
        assertFalse(shippingCouponUserDtos.get(0).isUsed());
        assertEquals("7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca", shippingCouponUserDtos.get(1).getId());
        assertFalse(shippingCouponUserDtos.get(1).isDeleted());
        assertTrue(shippingCouponUserDtos.get(1).isClaimed());
        assertFalse(shippingCouponUserDtos.get(1).isUsed());
        assertEquals("ca752e58-0387-4116-9f93-e9043db87b52", shippingCouponUserDtos.get(2).getId());
        assertFalse(shippingCouponUserDtos.get(2).isDeleted());
        assertTrue(shippingCouponUserDtos.get(2).isClaimed());
        assertFalse(shippingCouponUserDtos.get(2).isUsed());
    }
}