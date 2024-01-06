package com.calvinwan.shopeehomebackend.dao;

import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponUserDto;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;

import java.util.List;

public interface ShippingCouponDao {
    public String shopCreateCoupon(ShippingCouponDto shippingCouponDto);

    public String shopUpdateCouponById(String id, ShippingCouponDto shippingCouponDto);

    public String userClaimCoupon(String userId, String couponId);

    ShippingCoupon getById(String id);

    List<ShippingCoupon> shopGetByShopId(String shopId);

    List<ShippingCouponUserDto> userGetByShopId(String userId, String shopId);
}
