package com.calvinwan.shopeehomebackend.dto.coupon.seasoning;

import java.sql.Date;

public class SeasoningCouponUserDto {
    String id;
    Date date;
    String shopId;
    Double rate;
    boolean isClaimed;
    boolean isUsed;
    boolean isDeleted;

    public SeasoningCouponUserDto(String id, Date date, String shopId, Double rate, boolean isClaimed, boolean isUsed, boolean isDeleted) {
        this.id = id;
        this.date = date;
        this.shopId = shopId;
        this.rate = rate;
        this.isClaimed = isClaimed;
        this.isUsed = isUsed;
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean claimed) {
        isClaimed = claimed;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
