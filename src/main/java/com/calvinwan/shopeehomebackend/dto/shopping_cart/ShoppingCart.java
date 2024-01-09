package com.calvinwan.shopeehomebackend.dto.shopping_cart;

import java.util.List;

public class ShoppingCart {
    List<ShoppingCartShop> shops;

    public ShoppingCart() {
    }

    public ShoppingCart(List<ShoppingCartShop> shops) {
        this.shops = shops;
    }

    public List<ShoppingCartShop> getShops() {
        return shops;
    }

    public void setShops(List<ShoppingCartShop> shops) {
        this.shops = shops;
    }
}
