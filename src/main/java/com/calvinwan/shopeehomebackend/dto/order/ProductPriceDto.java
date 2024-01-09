package com.calvinwan.shopeehomebackend.dto.order;

import java.sql.Date;

public class ProductPriceDto {
    int price;
    Double discountRate;
    Date discountDate;

    public ProductPriceDto(int price, Double discountRate, Date discountDate) {
        this.price = price;
        this.discountRate = discountRate;
        this.discountDate = discountDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Date getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(Date discountDate) {
        this.discountDate = discountDate;
    }
}
