package com.calvinwan.shopeehomebackend.dto.coupon.shipping;

import java.sql.Date;

public class ShippingCouponDto {
    Date date;
    String shopId;
    int shippingLimit;
    boolean isDeleted;

    public ShippingCouponDto(Date date, String shopId, int shippingLimit, boolean isDeleted) {
        this.date = date;
        this.shopId = shopId;
        this.shippingLimit = shippingLimit;
        this.isDeleted = isDeleted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public int getShippingLimit() {
        return shippingLimit;
    }

    public void setShippingLimit(int shippingLimit) {
        this.shippingLimit = shippingLimit;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
