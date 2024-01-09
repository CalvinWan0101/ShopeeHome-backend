package com.calvinwan.shopeehomebackend.dto.order;

import java.util.List;

public class OrderCreateDto {
    String userId;
    String address;
    String couponId;
    List<OrderProductDto> products;

    public OrderCreateDto(String userId, String address, String couponId, List<OrderProductDto> products) {
        this.userId = userId;
        this.address = address;
        this.couponId = couponId;
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public List<OrderProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDto> products) {
        this.products = products;
    }
}
