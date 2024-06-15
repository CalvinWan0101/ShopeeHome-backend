package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ShippingCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponUserDto;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import tw.teddysoft.ezspec.keyword.Feature;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class ShippingCouponDaoImplementationSpec {

    @Autowired
    private ShippingCouponDao shippingCouponDao;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("Shipping Coupon Management", "As a shop or user\n" +
                "I want to manage shipping coupons\n" +
                "So that I can offer and claim discounts");
        feature.NewRule("Get Shipping Coupon by ID");
        feature.NewRule("Shop Get Shipping Coupons by Shop ID");
        feature.NewRule("User Get Shipping Coupons by Shop ID");
        feature.NewRule("Shop Create Coupon");
        feature.NewRule("Shop Update Coupon by ID");
        feature.NewRule("User Claim Coupon");
    }

    @Test
    public void get_by_id() {
        feature.newScenario("Get Shipping Coupon by ID").withRule("Get Shipping Coupon by ID")
                .Given("a shipping coupon ID", env -> {
                    env.put("id", "3bfd295f-3215-4585-b935-6e253ad1e54f");
                })
                .When("I retrieve the shipping coupon by ID", env -> {
                    env.put("shippingCoupon", shippingCouponDao.getById(env.gets("id")));
                })
                .ThenSuccess(env -> {
                    ShippingCoupon shippingCoupon = env.get("shippingCoupon", ShippingCoupon.class);
                    assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCoupon.getId());
                    assertEquals("2024-01-01", shippingCoupon.getDate().toString());
                    assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
                    assertFalse(shippingCoupon.isDeleted());
                    assertEquals(1000, shippingCoupon.getShippingLimit());
                })
                .Execute();
    }

    @Test
    public void shop_get_by_shop_id() {
        feature.newScenario("Shop Get Shipping Coupons by Shop ID").withRule("Shop Get Shipping Coupons by Shop ID")
                .Given("a shop ID", env -> {
                    env.put("shopId", "1013f7a0-0017-4c21-872f-c014914e6834");
                })
                .When("I retrieve the shipping coupons by shop ID", env -> {
                    env.put("shippingCoupons", shippingCouponDao.shopGetByShopId(env.gets("shopId")));
                })
                .ThenSuccess(env -> {
                    List<ShippingCoupon> shippingCoupons = env.get("shippingCoupons", List.class);
                    assertEquals(3, shippingCoupons.size());
                    assertEquals("ca752e58-0387-4116-9f93-e9043db87b52", shippingCoupons.get(0).getId());
                    assertEquals("3bfd295f-3215-4585-b935-6e253ad1e54f", shippingCoupons.get(1).getId());
                    assertEquals("7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca", shippingCoupons.get(2).getId());
                })
                .Execute();
    }

    @Test
    public void user_get_by_shop_id() {
        feature.newScenario("User Get Shipping Coupons by Shop ID").withRule("User Get Shipping Coupons by Shop ID")
                .Given("a user ID and a shop ID", env -> {
                    env.put("userId", "30e7e267-c791-424a-a94b-fa5e695d27e7");
                    env.put("shopId", "1013f7a0-0017-4c21-872f-c014914e6834");
                })
                .When("I retrieve the shipping coupons for the user by shop ID", env -> {
                    env.put("shippingCouponUserDtos", shippingCouponDao.userGetByShopId(env.gets("userId"), env.gets("shopId")));
                })
                .ThenSuccess(env -> {
                    List<ShippingCouponUserDto> shippingCouponUserDtos = env.get("shippingCouponUserDtos", List.class);
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
                })
                .Execute();
    }

    @Test
    @Transactional
    public void shop_create_coupon() {
        feature.newScenario("Shop Create Coupon").withRule("Shop Create Coupon")
                .Given("a new shipping coupon", env -> {
                    ShippingCouponDto shippingCouponDto = new ShippingCouponDto(
                            Date.valueOf("2024-01-01"),
                            "1013f7a0-0017-4c21-872f-c014914e6834",
                            1000,
                            false
                    );
                    env.put("shippingCouponDto", shippingCouponDto);
                })
                .When("I create the shipping coupon", env -> {
                    String id = shippingCouponDao.shopCreateCoupon(env.get("shippingCouponDto", ShippingCouponDto.class));
                    env.put("id", id);
                })
                .ThenSuccess(env -> {
                    ShippingCoupon shippingCoupon = shippingCouponDao.getById(env.gets("id"));
                    assertEquals(env.gets("id"), shippingCoupon.getId());
                    assertEquals(Date.valueOf("2024-01-01"), shippingCoupon.getDate());
                    assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
                    assertFalse(shippingCoupon.isDeleted());
                    assertEquals(1000, shippingCoupon.getShippingLimit());
                })
                .Execute();
    }

    @Test
    @Transactional
    public void shop_update_coupon_by_id() {
        feature.newScenario("Shop Update Coupon by ID").withRule("Shop Update Coupon by ID")
                .Given("an existing shipping coupon ID and new coupon details", env -> {
                    ShippingCouponDto shippingCouponDto = new ShippingCouponDto(
                            Date.valueOf("2024-01-01"),
                            "1013f7a0-0017-4c21-872f-c014914e6834",
                            1000,
                            false
                    );
                    env.put("shippingCouponDto", shippingCouponDto);
                    env.put("id", "3bfd295f-3215-4585-b935-6e253ad1e54f");
                })
                .When("I update the shipping coupon by ID", env -> {
                    String id = shippingCouponDao.shopUpdateCouponById(env.gets("id"), env.get("shippingCouponDto", ShippingCouponDto.class));
                    env.put("updatedId", id);
                })
                .ThenSuccess(env -> {
                    ShippingCoupon shippingCoupon = shippingCouponDao.getById(env.gets("updatedId"));
                    assertEquals(env.gets("updatedId"), shippingCoupon.getId());
                    assertEquals(Date.valueOf("2024-01-01"), shippingCoupon.getDate());
                    assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shippingCoupon.getShopId());
                    assertFalse(shippingCoupon.isDeleted());
                    assertEquals(1000, shippingCoupon.getShippingLimit());
                })
                .Execute();
    }

    @Test
    @Transactional
    public void user_claim_coupon() {
        feature.newScenario("User Claim Coupon").withRule("User Claim Coupon")
                .Given("a user ID and a coupon ID to claim", env -> {
                    env.put("userId", "30e7e267-c791-424a-a94b-fa5e695d27e7");
                    env.put("couponId", "ca752e58-0387-4116-9f93-e9043db87b52");
                })
                .When("I claim the coupon for the user", env -> {
                    shippingCouponDao.userClaimCoupon(env.gets("userId"), env.gets("couponId"));
                })
                .ThenSuccess(env -> {
                    List<ShippingCouponUserDto> shippingCouponUserDtos = shippingCouponDao.userGetByShopId(env.gets("userId"), "1013f7a0-0017-4c21-872f-c014914e6834");
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
                })
                .Execute();
    }
}
