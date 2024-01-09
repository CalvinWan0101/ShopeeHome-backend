package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dao.ShoppingCartDao;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCart;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartSingleProduct;
import com.calvinwan.shopeehomebackend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart getShoppingCart(String userId) {
        return shoppingCartDao.getShoppingCart(userId);
    }

    @Override
    public void updateShoppingCart(String userId, ShoppingCart shoppingCart) {
        shoppingCartDao.updateShoppingCart(userId, shoppingCart);
    }

    @Override
    public void addProductToShoppingCart(ShoppingCartSingleProduct shoppingCartSingleProduct) {
        shoppingCartDao.addProductToShoppingCart(shoppingCartSingleProduct);
    }
}
