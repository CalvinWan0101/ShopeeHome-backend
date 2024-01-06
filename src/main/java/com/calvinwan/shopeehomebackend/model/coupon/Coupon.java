package com.calvinwan.shopeehomebackend.model.coupon;

import java.sql.Date;

public abstract class Coupon {
    String id;
    Date date;
    String shopId;
    boolean isDeleted;

    public Coupon(String id, Date date, String shopId, boolean isDeleted) {
        this.id = id;
        this.date = date;
        this.shopId = shopId;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
