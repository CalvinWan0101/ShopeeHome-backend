package com.calvinwan.shopeehomebackend.dto.shopping_cart;

import java.sql.Date;

public class ShoppingCartDto {
    String shopId;
    String shopName;
    String productId;
    String productName;
    String productImage;
    int quantity;
    int quantityLimit;
    int price;
    Double discountRate;
    Date discountDate;

    public ShoppingCartDto(String shopId, String shopName, String productId, String productName, String productImage, int quantity, int quantityLimit, int price, Double discountRate, Date discountDate) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.quantity = quantity;
        this.quantityLimit = quantityLimit;
        this.price = price;
        this.discountRate = discountRate;
        this.discountDate = discountDate;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(int quantityLimit) {
        this.quantityLimit = quantityLimit;
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
