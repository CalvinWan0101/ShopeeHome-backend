package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCart;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartSingleProduct;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(String userId);

    void updateShoppingCart(String userId, ShoppingCart shoppingCart);

    void addProductToShoppingCart(ShoppingCartSingleProduct shoppingCartSingleProduct);
}
