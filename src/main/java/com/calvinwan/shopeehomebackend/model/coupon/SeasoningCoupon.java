package com.calvinwan.shopeehomebackend.model.coupon;

import java.sql.Date;

public class SeasoningCoupon extends Coupon {
    Double rate;

    public SeasoningCoupon(String id, Date date, String shopId, boolean isDeleted, Double rate) {
        super(id, date, shopId, isDeleted);
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
