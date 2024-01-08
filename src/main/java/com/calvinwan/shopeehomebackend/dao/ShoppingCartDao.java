package com.calvinwan.shopeehomebackend.dao;

import com.calvinwan.shopeehomebackend.dto.shopping_cart.Refresh;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart getShoppingCart(String userId);

    Refresh updateShoppingCart(String userId, ShoppingCart shoppingCart);
}
