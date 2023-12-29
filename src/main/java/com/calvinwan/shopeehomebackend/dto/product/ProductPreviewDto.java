package com.calvinwan.shopeehomebackend.dto.product;

public class ProductPreviewDto {
    String name;
    int finalPrice;
    int sales;
    String image;

    public ProductPreviewDto(String name, int finalPrice, int sales, String image) {
        this.name = name;
        this.finalPrice = finalPrice;
        this.sales = sales;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
