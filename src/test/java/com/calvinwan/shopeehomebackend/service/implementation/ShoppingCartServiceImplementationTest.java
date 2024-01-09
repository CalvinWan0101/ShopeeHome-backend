package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCart;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartProduct;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartShop;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartSingleProduct;
import com.calvinwan.shopeehomebackend.service.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class ShoppingCartServiceImplementationTest {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Test
    public void get_shopping_cart() {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart("30e7e267-c791-424a-a94b-fa5e695d27e7");

        assertEquals(2, shoppingCart.getShops().size());

        ShoppingCartShop shop1 = shoppingCart.getShops().get(0);
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shop1.getId());
        assertEquals("shop1", shop1.getName());
        assertEquals(2, shop1.getProducts().size());
        ShoppingCartShop shop2 = shoppingCart.getShops().get(1);
        assertEquals("f0694ecf-6282-48f9-a401-49eb08067ce0", shop2.getId());
        assertEquals("shop2", shop2.getName());
        assertEquals(2, shop2.getProducts().size());

        ShoppingCartProduct product1;
        ShoppingCartProduct product2;

        product1 = shop1.getProducts().get(0);
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", product1.getId());
        assertEquals("iphone", product1.getName());
        assertEquals("iphone_image_1", product1.getImage());
        assertEquals(1, product1.getQuantity());
        assertEquals(90, product1.getQuantityLimit());
        assertEquals(32103, product1.getPrice());
        product2 = shop1.getProducts().get(1);
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", product2.getId());
        assertEquals("xiaomi", product2.getName());
        assertEquals("xiaomi_image_1", product2.getImage());
        assertEquals(2, product2.getQuantity());
        assertEquals(140, product2.getQuantityLimit());
        assertEquals(17999, product2.getPrice());

        product1 = shop2.getProducts().get(0);
        assertEquals("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", product1.getId());
        assertEquals("tissue", product1.getName());
        assertEquals("tissue_image_1", product1.getImage());
        assertEquals(3, product1.getQuantity());
        assertEquals(52123, product1.getQuantityLimit());
        assertEquals(100, product1.getPrice());
        product2 = shop2.getProducts().get(1);
        assertEquals("9595f97a-bf11-488a-8c15-9edf4db1c450", product2.getId());
        assertEquals("toothbrush", product2.getName());
        assertEquals("toothbrush_image_1", product2.getImage());
        assertEquals(4, product2.getQuantity());
        assertEquals(279123, product2.getQuantityLimit());
        assertEquals(50, product2.getPrice());
    }

    @Test
    @Transactional
    public void update_shopping_cart() {
        ShoppingCart shoppingCartOrigin = shoppingCartService.getShoppingCart("30e7e267-c791-424a-a94b-fa5e695d27e7");
        shoppingCartOrigin.getShops().get(0).getProducts().get(0).setQuantity(100);
        shoppingCartOrigin.getShops().get(1).getProducts().remove(0);
        shoppingCartService.updateShoppingCart("30e7e267-c791-424a-a94b-fa5e695d27e7", shoppingCartOrigin);
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart("30e7e267-c791-424a-a94b-fa5e695d27e7");


        assertEquals(2, shoppingCart.getShops().size());

        ShoppingCartShop shop1 = shoppingCart.getShops().get(0);
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shop1.getId());
        assertEquals("shop1", shop1.getName());
        assertEquals(2, shop1.getProducts().size());
        ShoppingCartShop shop2 = shoppingCart.getShops().get(1);
        assertEquals("f0694ecf-6282-48f9-a401-49eb08067ce0", shop2.getId());
        assertEquals("shop2", shop2.getName());
        assertEquals(1, shop2.getProducts().size());

        ShoppingCartProduct product1;
        ShoppingCartProduct product2;

        product1 = shop1.getProducts().get(0);
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", product1.getId());
        assertEquals("iphone", product1.getName());
        assertEquals("iphone_image_1", product1.getImage());
        assertEquals(100, product1.getQuantity());
        assertEquals(90, product1.getQuantityLimit());
        assertEquals(32103, product1.getPrice());
        product2 = shop1.getProducts().get(1);
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", product2.getId());
        assertEquals("xiaomi", product2.getName());
        assertEquals("xiaomi_image_1", product2.getImage());
        assertEquals(2, product2.getQuantity());
        assertEquals(140, product2.getQuantityLimit());
        assertEquals(17999, product2.getPrice());

        product1 = shop2.getProducts().get(0);
        assertEquals("9595f97a-bf11-488a-8c15-9edf4db1c450", product1.getId());
        assertEquals("toothbrush", product1.getName());
        assertEquals("toothbrush_image_1", product1.getImage());
        assertEquals(4, product1.getQuantity());
        assertEquals(279123, product1.getQuantityLimit());
        assertEquals(50, product1.getPrice());
    }

    @Test
    @Transactional
    public void add_a_already_exist_product_into_shopping_cart() {
        shoppingCartService.addProductToShoppingCart(new ShoppingCartSingleProduct(
                "30e7e267-c791-424a-a94b-fa5e695d27e7",
                "6874ada1-747f-41a7-bb9a-613d2ec0ce1d",
                29));
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart("30e7e267-c791-424a-a94b-fa5e695d27e7");

        assertEquals(2, shoppingCart.getShops().size());

        ShoppingCartShop shop1 = shoppingCart.getShops().get(0);
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shop1.getId());
        assertEquals("shop1", shop1.getName());
        assertEquals(2, shop1.getProducts().size());
        ShoppingCartShop shop2 = shoppingCart.getShops().get(1);
        assertEquals("f0694ecf-6282-48f9-a401-49eb08067ce0", shop2.getId());
        assertEquals("shop2", shop2.getName());
        assertEquals(2, shop2.getProducts().size());

        ShoppingCartProduct product1;
        ShoppingCartProduct product2;

        product1 = shop1.getProducts().get(0);
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", product1.getId());
        assertEquals("iphone", product1.getName());
        assertEquals("iphone_image_1", product1.getImage());
        assertEquals(30, product1.getQuantity());
        assertEquals(90, product1.getQuantityLimit());
        assertEquals(32103, product1.getPrice());
        product2 = shop1.getProducts().get(1);
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", product2.getId());
        assertEquals("xiaomi", product2.getName());
        assertEquals("xiaomi_image_1", product2.getImage());
        assertEquals(2, product2.getQuantity());
        assertEquals(140, product2.getQuantityLimit());
        assertEquals(17999, product2.getPrice());

        product1 = shop2.getProducts().get(0);
        assertEquals("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", product1.getId());
        assertEquals("tissue", product1.getName());
        assertEquals("tissue_image_1", product1.getImage());
        assertEquals(3, product1.getQuantity());
        assertEquals(52123, product1.getQuantityLimit());
        assertEquals(100, product1.getPrice());
        product2 = shop2.getProducts().get(1);
        assertEquals("9595f97a-bf11-488a-8c15-9edf4db1c450", product2.getId());
        assertEquals("toothbrush", product2.getName());
        assertEquals("toothbrush_image_1", product2.getImage());
        assertEquals(4, product2.getQuantity());
        assertEquals(279123, product2.getQuantityLimit());
        assertEquals(50, product2.getPrice());
    }

    @Test
    @Transactional
    public void add_a_not_exist_product_into_shopping_cart() {
        shoppingCartService.addProductToShoppingCart(new ShoppingCartSingleProduct(
                "b8007834-0db6-4e8a-aa98-7833035bcaa0",
                "6874ada1-747f-41a7-bb9a-613d2ec0ce1d",
                87));

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart("b8007834-0db6-4e8a-aa98-7833035bcaa0");

        assertEquals(1, shoppingCart.getShops().size());

        ShoppingCartShop shop1 = shoppingCart.getShops().get(0);
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shop1.getId());
        assertEquals("shop1", shop1.getName());
        assertEquals(1, shop1.getProducts().size());

        ShoppingCartProduct product1;

        product1 = shop1.getProducts().get(0);
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", product1.getId());
        assertEquals("iphone", product1.getName());
        assertEquals("iphone_image_1", product1.getImage());
        assertEquals(87, product1.getQuantity());
        assertEquals(90, product1.getQuantityLimit());
        assertEquals(32103, product1.getPrice());
    }

}