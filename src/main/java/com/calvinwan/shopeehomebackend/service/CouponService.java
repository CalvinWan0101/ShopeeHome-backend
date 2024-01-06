package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.coupon.CouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.CouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponDto;
import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;

public interface CouponService {
    public String createSeasoningCoupon(SeasoningCouponDto seasoningCouponDto);

    public String createShippingCoupon(ShippingCouponDto shippingCouponDto);

    public String updateSeasoningCouponById(String id, SeasoningCouponDto seasoningCouponDto);

    public String updateShippingCouponById(String id, ShippingCouponDto shippingCouponDto);

    public String userClaimCoupon(String userId, String couponId);

    public SeasoningCoupon getSeasoningCouponById(String id);

    public ShippingCoupon getShippingCouponById(String id);

    public CouponDto shopGetByShopId(String shopId);

    public CouponUserDto userGetByShopId(String userId, String shopId);

    public void deleteById(String id);
}
