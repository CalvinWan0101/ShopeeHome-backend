package com.calvinwan.shopeehomebackend.dto.coupon;

import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;

import java.util.List;

public class CouponDto {
    List<SeasoningCoupon> seasoningCoupons;
    List<ShippingCoupon> shippingCoupons;

    public CouponDto(List<SeasoningCoupon> seasoningCoupons, List<ShippingCoupon> shippingCoupons) {
        this.seasoningCoupons = seasoningCoupons;
        this.shippingCoupons = shippingCoupons;
    }

    public List<SeasoningCoupon> getSeasoningCoupons() {
        return seasoningCoupons;
    }

    public void setSeasoningCoupons(List<SeasoningCoupon> seasoningCoupons) {
        this.seasoningCoupons = seasoningCoupons;
    }

    public List<ShippingCoupon> getShippingCoupons() {
        return shippingCoupons;
    }

    public void setShippingCoupons(List<ShippingCoupon> shippingCoupons) {
        this.shippingCoupons = shippingCoupons;
    }
}
