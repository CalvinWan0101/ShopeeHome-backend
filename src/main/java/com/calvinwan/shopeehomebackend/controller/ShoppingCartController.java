package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCart;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartSingleProduct;
import com.calvinwan.shopeehomebackend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/shopping_cart/{userId}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable String userId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCart);
    }

    @PutMapping("/shopping_cart/{userId}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable String userId,
                                                           @RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.updateShoppingCart(userId, shoppingCart);
        ShoppingCart updatedShoppingCart = shoppingCartService.getShoppingCart(userId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedShoppingCart);
    }

    @PutMapping("/shopping_cart/product")
    public ResponseEntity<ShoppingCart> addProductToShoppingCart(@RequestBody ShoppingCartSingleProduct shoppingCartSingleProduct) {
        shoppingCartService.addProductToShoppingCart(shoppingCartSingleProduct);
        ShoppingCart updatedShoppingCart = shoppingCartService.getShoppingCart(shoppingCartSingleProduct.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(updatedShoppingCart);
    }
}
