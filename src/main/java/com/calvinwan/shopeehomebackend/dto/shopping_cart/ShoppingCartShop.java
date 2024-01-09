package com.calvinwan.shopeehomebackend.dto.shopping_cart;

import java.util.List;

public class ShoppingCartShop {
    String id;
    String name;
    List<ShoppingCartProduct> products;

    public ShoppingCartShop() {
    }

    public ShoppingCartShop(String id, String name, List<ShoppingCartProduct> products) {
        this.id = id;
        this.name = name;
        this.products = products;
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

    public List<ShoppingCartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ShoppingCartProduct> products) {
        this.products = products;
    }
}
