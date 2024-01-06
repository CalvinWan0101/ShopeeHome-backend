package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.dto.coupon.CouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.CouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponDto;
import com.calvinwan.shopeehomebackend.model.coupon.SeasoningCoupon;
import com.calvinwan.shopeehomebackend.model.coupon.ShippingCoupon;
import com.calvinwan.shopeehomebackend.service.CouponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @PostMapping("/coupon/seasoning")
    public ResponseEntity<SeasoningCoupon> createSeasoningCoupon(@RequestBody @Valid SeasoningCouponDto seasoningCouponDto) {
        String id = couponService.createSeasoningCoupon(seasoningCouponDto);
        SeasoningCoupon seasoningCoupon = couponService.getSeasoningCouponById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(seasoningCoupon);
    }

    @PostMapping("/coupon/shipping")
    public ResponseEntity<ShippingCoupon> createShippingCoupon(@RequestBody @Valid ShippingCouponDto shippingCouponDto) {
        String id = couponService.createShippingCoupon(shippingCouponDto);
        ShippingCoupon shippingCoupon = couponService.getShippingCouponById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(shippingCoupon);
    }

    @GetMapping("/coupon/seasoning/{id}")
    public ResponseEntity<SeasoningCoupon> getSeasoningCouponById(@PathVariable String id) {
        SeasoningCoupon seasoningCoupon = couponService.getSeasoningCouponById(id);
        if (seasoningCoupon != null) {
            return ResponseEntity.status(HttpStatus.OK).body(seasoningCoupon);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/coupon/shipping/{id}")
    public ResponseEntity<ShippingCoupon> getShippingCouponById(@PathVariable String id) {
        ShippingCoupon shippingCoupon = couponService.getShippingCouponById(id);
        if (shippingCoupon != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shippingCoupon);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/coupon/shop/{shopId}")
    public ResponseEntity<CouponDto> shopGetByShopId(@PathVariable String shopId) {
        CouponDto couponDto = couponService.shopGetByShopId(shopId);
        if (couponDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(couponDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/coupon/user/{userId}/shop/{shopId}")
    public ResponseEntity<CouponUserDto> userGetByShopId(@PathVariable String userId, @PathVariable String shopId) {
        CouponUserDto couponUserDto = couponService.userGetByShopId(userId, shopId);
        if (couponUserDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(couponUserDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/coupon/seasoning/{id}")
    public ResponseEntity<SeasoningCoupon> updateSeasoningCouponById(@PathVariable String id, @RequestBody @Valid SeasoningCouponDto seasoningCouponDto) {
        String updatedId = couponService.updateSeasoningCouponById(id, seasoningCouponDto);
        SeasoningCoupon seasoningCoupon = couponService.getSeasoningCouponById(updatedId);
        return ResponseEntity.status(HttpStatus.OK).body(seasoningCoupon);
    }

    @PutMapping("/coupon/shipping/{id}")
    public ResponseEntity<ShippingCoupon> updateShippingCouponById(@PathVariable String id, @RequestBody @Valid ShippingCouponDto shippingCouponDto) {
        String updatedId = couponService.updateShippingCouponById(id, shippingCouponDto);
        ShippingCoupon shippingCoupon = couponService.getShippingCouponById(updatedId);
        return ResponseEntity.status(HttpStatus.OK).body(shippingCoupon);
    }

    @PutMapping("/coupon/user/{userId}/coupon/{couponId}")
    public ResponseEntity<String> userClaimCoupon(@PathVariable String userId, @PathVariable String couponId) {
        String updatedId = couponService.userClaimCoupon(userId, couponId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedId);
    }

    @DeleteMapping("/coupon/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        couponService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
