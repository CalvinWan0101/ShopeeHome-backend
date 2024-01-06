package com.calvinwan.shopeehomebackend.dto.coupon.shipping;

import java.sql.Date;

public class ShippingCouponUserDto {
    String id;
    Date date;
    String shopId;
    int shippingLimit;
    boolean isClaimed;
    boolean isUsed;
    boolean isDeleted;

    public ShippingCouponUserDto(String id, Date date, String shopId, int shippingLimit, boolean isClaimed, boolean isUsed, boolean isDeleted) {
        this.id = id;
        this.date = date;
        this.shopId = shopId;
        this.shippingLimit = shippingLimit;
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

    public int getShippingLimit() {
        return shippingLimit;
    }

    public void setShippingLimit(int shippingLimit) {
        this.shippingLimit = shippingLimit;
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
