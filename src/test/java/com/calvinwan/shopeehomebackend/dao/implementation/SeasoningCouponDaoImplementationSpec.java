package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.SeasoningCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponDto;
import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import tw.teddysoft.ezspec.extension.junit5.EzScenario;
import tw.teddysoft.ezspec.keyword.Feature;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class SeasoningCouponDaoImplementationSpec {

    @Autowired
    private SeasoningCouponDao seasoningCouponDao;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("Seasoning Coupon Management", "As a shop or user\n" + "I want to manage seasoning coupons\n" + "So that I can offer and claim discounts");
        feature.NewRule("Get Seasoning Coupon by ID");
        feature.NewRule("Shop Get Seasoning Coupons by Shop ID");
        feature.NewRule("User Get Seasoning Coupons by Shop ID");
        feature.NewRule("Shop Create Coupon");
        feature.NewRule("Shop Update Coupon by ID");
        feature.NewRule("User Claim Coupon");
        feature.NewRule("Delete Coupon by ID");
    }

    @EzScenario
    public void get_by_id() {
        feature.newScenario("Get Seasoning Coupon by ID").withRule("Get Seasoning Coupon by ID")
                .Given("a seasoning coupon ID", env -> {
                    env.put("id", "efbec3f1-563b-4b71-892b-a6db85bf76dc");
                })
                .When("I retrieve the seasoning coupon by ID", env -> {
                    env.put("seasoningCoupon", seasoningCouponDao.getById(env.gets("id")));
                })
                .ThenSuccess(env -> {
                    SeasoningCoupon seasoningCoupon = env.get("seasoningCoupon", SeasoningCoupon.class);
                    assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCoupon.getId());
                    assertEquals(Date.valueOf("2024-03-01"), seasoningCoupon.getDate());
                    assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
                    assertFalse(seasoningCoupon.isDeleted());
                    assertEquals(0.1, seasoningCoupon.getRate());
                })
                .Execute();
    }

    @EzScenario
    public void shop_get_by_shop_id() {
        feature.newScenario("Shop Get Seasoning Coupons by Shop ID").withRule("Shop Get Seasoning Coupons by Shop ID")
                .Given("a shop ID", env -> {
                    env.put("shopId", "1013f7a0-0017-4c21-872f-c014914e6834");
                })
                .When("I retrieve the seasoning coupons by shop ID", env -> {
                    env.put("seasoningCoupons", seasoningCouponDao.shopGetByShopId(env.gets("shopId")));
                })
                .ThenSuccess(env -> {
                    List<SeasoningCoupon> seasoningCoupons = env.get("seasoningCoupons", List.class);
                    seasoningCoupons.stream().sorted((a, b) -> a.getId().compareTo(b.getId()));
                    assertEquals(2, seasoningCoupons.size());
                    assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCoupons.get(0).getId());
                    assertEquals("838db05f-07fe-44e4-907f-cf7919cf6777", seasoningCoupons.get(1).getId());
                })
                .Execute();
    }

//    @EzScenario
//    public void user_get_by_shop_id() {
//        feature.newScenario("User Get Seasoning Coupons by Shop ID").withRule("User Get Seasoning Coupons by Shop ID")
//                .Given("a user ID and a shop ID", env -> {
//                    env.put("userId", "30e7e267-c791-424a-a94b-fa5e695d27e7");
//                    env.put("shopId", "1013f7a0-0017-4c21-872f-c014914e6834");
//                })
//                .When("I retrieve the seasoning coupons for the user by shop ID", env -> {
//                    env.put("seasoningCouponUserDtos", seasoningCouponDao.userGetByShopId(env.gets("userId"), env.gets("shopId")));
//                })
//                .ThenSuccess(env -> {
//                    List<SeasoningCouponUserDto> seasoningCouponUserDtos = env.get("seasoningCouponUserDtos", List.class);
//                    assertEquals(2, seasoningCouponUserDtos.size());
//                    assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCouponUserDtos.get(0).getId());
//                    assertFalse(seasoningCouponUserDtos.get(0).isDeleted());
//                    assertTrue(seasoningCouponUserDtos.get(0).isClaimed());
//                    assertFalse(seasoningCouponUserDtos.get(0).isUsed());
//                    assertEquals("838db05f-07fe-44e4-907f-cf7919cf6777", seasoningCouponUserDtos.get(1).getId());
//                    assertFalse(seasoningCouponUserDtos.get(1).isDeleted());
//                    assertFalse(seasoningCouponUserDtos.get(1).isClaimed());
//                    assertFalse(seasoningCouponUserDtos.get(1).isUsed());
//                })
//                .Execute();
//    }

    @EzScenario
    @Transactional
    public void shop_create_coupon() {
        feature.newScenario("Shop Create Coupon").withRule("Shop Create Coupon")
                .Given("a new seasoning coupon", env -> {
                    SeasoningCouponDto seasoningCouponDto = new SeasoningCouponDto(Date.valueOf("2021-01-01"), "1013f7a0-0017-4c21-872f-c014914e6834", 0.98, false);
                    env.put("seasoningCouponDto", seasoningCouponDto);
                })
                .When("I create the seasoning coupon", env -> {
                    String id = seasoningCouponDao.shopCreateCoupon(env.get("seasoningCouponDto", SeasoningCouponDto.class));
                    env.put("id", id);
                })
                .ThenSuccess(env -> {
                    SeasoningCoupon seasoningCoupon = seasoningCouponDao.getById(env.gets("id"));
                    assertEquals(env.gets("id"), seasoningCoupon.getId());
                    assertEquals(Date.valueOf("2021-01-01"), seasoningCoupon.getDate());
                    assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
                    assertFalse(seasoningCoupon.isDeleted());
                    assertEquals(0.98, seasoningCoupon.getRate());
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void shop_update_coupon_by_id() {
        feature.newScenario("Shop Update Coupon by ID").withRule("Shop Update Coupon by ID")
                .Given("an existing seasoning coupon ID and new coupon details", env -> {
                    SeasoningCouponDto seasoningCouponDto = new SeasoningCouponDto(Date.valueOf("2021-01-01"), "1013f7a0-0017-4c21-872f-c014914e6834", 0.98, false);
                    env.put("seasoningCouponDto", seasoningCouponDto);
                    env.put("id", "efbec3f1-563b-4b71-892b-a6db85bf76dc");
                })
                .When("I update the seasoning coupon by ID", env -> {
                    String id = seasoningCouponDao.shopUpdateCouponById(env.gets("id"), env.get("seasoningCouponDto", SeasoningCouponDto.class));
                    env.put("updatedId", id);
                })
                .ThenSuccess(env -> {
                    SeasoningCoupon seasoningCoupon = seasoningCouponDao.getById(env.gets("updatedId"));
                    assertEquals(env.gets("updatedId"), seasoningCoupon.getId());
                    assertEquals(Date.valueOf("2021-01-01"), seasoningCoupon.getDate());
                    assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", seasoningCoupon.getShopId());
                    assertFalse(seasoningCoupon.isDeleted());
                    assertEquals(0.98, seasoningCoupon.getRate());
                })
                .Execute();
    }

//    @EzScenario
//    @Transactional
//    public void user_claim_coupon() {
//        feature.newScenario("User Claim Coupon").withRule("User Claim Coupon")
//                .Given("a user ID and a coupon ID to claim", env -> {
//                    env.put("userId", "30e7e267-c791-424a-a94b-fa5e695d27e7");
//                    env.put("couponId", "ca752e58-0387-4116-9f93-e9043db87b52");
//                })
//                .When("I claim the coupon for the user", env -> {
//                    seasoningCouponDao.userClaimCoupon(env.gets("userId"), env.gets("couponId"));
//                })
//                .ThenSuccess(env -> {
//                    List<SeasoningCouponUserDto> seasoningCouponUserDtos = seasoningCouponDao.userGetByShopId(env.gets("userId"), "1013f7a0-0017-4c21-872f-c014914e6834");
//                    assertEquals(2, seasoningCouponUserDtos.size());
//                    assertEquals("efbec3f1-563b-4b71-892b-a6db85bf76dc", seasoningCouponUserDtos.get(0).getId());
//                    assertFalse(seasoningCouponUserDtos.get(0).isDeleted());
//                    assertTrue(seasoningCouponUserDtos.get(0).isClaimed());
//                    assertFalse(seasoningCouponUserDtos.get(0).isUsed());
//                    assertEquals("838db05f-07fe-44e4-907f-cf7919cf6777", seasoningCouponUserDtos.get(1).getId());
//                    assertFalse(seasoningCouponUserDtos.get(1).isDeleted());
//                    assertFalse(seasoningCouponUserDtos.get(1).isClaimed());
//                    assertFalse(seasoningCouponUserDtos.get(1).isUsed());
//                })
//                .Execute();
//    }

    @EzScenario
    @Transactional
    public void delete_by_id() {
        feature.newScenario("Delete Coupon by ID").withRule("Delete Coupon by ID")
                .Given("an existing seasoning coupon ID", env -> {
                    env.put("id", "efbec3f1-563b-4b71-892b-a6db85bf76dc");
                })
                .When("I delete the seasoning coupon by ID", env -> {
                    seasoningCouponDao.deleteById(env.gets("id"));
                })
                .ThenSuccess(env -> {
                    SeasoningCoupon seasoningCoupon = seasoningCouponDao.getById(env.gets("id"));
                    assertTrue(seasoningCoupon.isDeleted());
                })
                .Execute();
    }
}
