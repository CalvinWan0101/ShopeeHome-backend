package com.calvinwan.shopeehomebackend.dto.shopping_cart;

public class ShoppingCartProduct {
    String id;
    String name;
    String image;
    int quantity;
    int quantityLimit;
    int price;

    public ShoppingCartProduct(String id, String name, String image, int quantity, int quantityLimit, int price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.quantityLimit = quantityLimit;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
