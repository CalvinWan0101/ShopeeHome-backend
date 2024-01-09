package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(String userId);

    void updateShoppingCart(String userId, ShoppingCart shoppingCart);
}
