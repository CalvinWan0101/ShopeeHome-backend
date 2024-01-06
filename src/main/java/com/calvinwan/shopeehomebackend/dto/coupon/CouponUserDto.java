package com.calvinwan.shopeehomebackend.dto.coupon;

import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponUserDto;

import java.util.List;

public class CouponUserDto {
    List<SeasoningCouponUserDto> seasoningCouponUserDtos;
    List<ShippingCouponUserDto> shippingCouponUserDtos;

    public CouponUserDto(List<SeasoningCouponUserDto> seasoningCouponUserDtos, List<ShippingCouponUserDto> shippingCouponUserDtos) {
        this.seasoningCouponUserDtos = seasoningCouponUserDtos;
        this.shippingCouponUserDtos = shippingCouponUserDtos;
    }

    public List<SeasoningCouponUserDto> getSeasoningCouponUserDtos() {
        return seasoningCouponUserDtos;
    }

    public void setSeasoningCouponUserDtos(List<SeasoningCouponUserDto> seasoningCouponUserDtos) {
        this.seasoningCouponUserDtos = seasoningCouponUserDtos;
    }

    public List<ShippingCouponUserDto> getShippingCouponUserDtos() {
        return shippingCouponUserDtos;
    }

    public void setShippingCouponUserDtos(List<ShippingCouponUserDto> shippingCouponUserDtos) {
        this.shippingCouponUserDtos = shippingCouponUserDtos;
    }
}
