package com.calvinwan.shopeehomebackend.dao;

import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponUserDto;
import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;

import java.util.List;

public interface SeasoningCouponDao {

    public String shopCreateCoupon(SeasoningCouponDto seasoningCouponDto);

    public String shopUpdateCouponById(String id, SeasoningCouponDto seasoningCouponDto);

    public String userClaimCoupon(String userId, String couponId);

    SeasoningCoupon getById(String id);

    List<SeasoningCoupon> shopGetByShopId(String shopId);

    List<SeasoningCouponUserDto> userGetByShopId(String userId, String shopId);

    void deleteById(String id);
}
