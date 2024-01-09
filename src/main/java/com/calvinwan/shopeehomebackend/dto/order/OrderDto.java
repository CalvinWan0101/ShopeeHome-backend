package com.calvinwan.shopeehomebackend.dto.order;

import com.calvinwan.shopeehomebackend.model.order.OrderProduct;

import java.sql.Date;
import java.util.List;

public class OrderDto {
    String id;
    String userId;
    String couponId;
    String address;
    int totalPrice;
    int shippingCost;
    Double rate;
    Integer shippingLimit;
    Date startTime;
    Date deliverTime;
    String productId;
    String productName;
    int productPrice;
    int productQuantity;
    String productImage;

    public OrderDto(String id, String userId, String couponId, String address, int totalPrice, int shippingCost, Double rate, Integer shippingLimit, Date startTime, Date deliverTime, String productId, String productName, int productPrice, int productQuantity, String productImage) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingCost = shippingCost;
        this.rate = rate;
        this.shippingLimit = shippingLimit;
        this.startTime = startTime;
        this.deliverTime = deliverTime;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getShippingLimit() {
        return shippingLimit;
    }

    public void setShippingLimit(Integer shippingLimit) {
        this.shippingLimit = shippingLimit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
