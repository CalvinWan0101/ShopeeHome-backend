package com.calvinwan.shopeehomebackend.model.coupon;

import java.sql.Date;

public class ShippingCoupon extends Coupon {
    int shippingLimit;

    public ShippingCoupon(String id, Date date, String shopId, boolean isDeleted, int shippingLimit) {
        super(id, date, shopId, isDeleted);
        this.shippingLimit = shippingLimit;
    }

    public int getShippingLimit() {
        return shippingLimit;
    }

    public void setShippingLimit(int shippingLimit) {
        this.shippingLimit = shippingLimit;
    }
}
