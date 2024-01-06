package com.calvinwan.shopeehomebackend.dto.coupon.seasoning;

import java.sql.Date;

public class SeasoningCouponDto {
    Date date;
    String shopId;
    Double rate;
    boolean isDeleted;

    public SeasoningCouponDto(Date date, String shopId, Double rate, boolean isDeleted) {
        this.date = date;
        this.shopId = shopId;
        this.rate = rate;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
