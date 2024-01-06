package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dao.SeasoningCouponDao;
import com.calvinwan.shopeehomebackend.dao.ShippingCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.CouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.CouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponUserDto;
import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;
import com.calvinwan.shopeehomebackend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImplementation implements CouponService {


    @Autowired
    private SeasoningCouponDao seasoningCouponDao;

    @Autowired
    private ShippingCouponDao shippingCouponDao;

    @Override
    public String createSeasoningCoupon(SeasoningCouponDto seasoningCouponDto) {
        return seasoningCouponDao.shopCreateCoupon(seasoningCouponDto);
    }

    @Override
    public String createShippingCoupon(ShippingCouponDto shippingCouponDto) {
        return shippingCouponDao.shopCreateCoupon(shippingCouponDto);
    }

    @Override
    public String updateSeasoningCouponById(String id, SeasoningCouponDto seasoningCouponDto) {
        return seasoningCouponDao.shopUpdateCouponById(id, seasoningCouponDto);
    }

    @Override
    public String updateShippingCouponById(String id, ShippingCouponDto shippingCouponDto) {
        return shippingCouponDao.shopUpdateCouponById(id, shippingCouponDto);
    }

    @Override
    public String userClaimCoupon(String userId, String couponId) {
        return seasoningCouponDao.userClaimCoupon(userId, couponId);
    }

    @Override
    public SeasoningCoupon getSeasoningCouponById(String id) {
        return seasoningCouponDao.getById(id);
    }

    @Override
    public ShippingCoupon getShippingCouponById(String id) {
        return shippingCouponDao.getById(id);
    }

    @Override
    public CouponDto shopGetByShopId(String shopId) {
        List<SeasoningCoupon> seasoningCoupons = seasoningCouponDao.shopGetByShopId(shopId);
        List<ShippingCoupon> shippingCoupons = shippingCouponDao.shopGetByShopId(shopId);
        return new CouponDto(seasoningCoupons, shippingCoupons);
    }

    @Override
    public CouponUserDto userGetByShopId(String userId, String shopId) {
        List<SeasoningCouponUserDto> seasoningCoupons = seasoningCouponDao.userGetByShopId(userId, shopId);
        List<ShippingCouponUserDto> shippingCoupons = shippingCouponDao.userGetByShopId(userId, shopId);
        return new CouponUserDto(seasoningCoupons, shippingCoupons);
    }

    @Override
    public void deleteById(String id) {
        seasoningCouponDao.deleteById(id);
    }
}
