package com.calvinwan.shopeehomebackend.dto.product;

import java.sql.Date;
import java.util.List;

public class ProductDto {
    private String name;
    private int amount;
    private int price;
    private String description;
    private Double discountRate;
    private Date discountDate;
    private String shopId;
    private List<String> images;
    private boolean isDeleted;

    public ProductDto(String name, int amount, int price, String description, Double discountRate, Date discountDate, String shopId, List<String> images, boolean isDeleted) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.discountRate = discountRate;
        this.discountDate = discountDate;
        this.shopId = shopId;
        this.images = images;
        this.isDeleted = isDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
