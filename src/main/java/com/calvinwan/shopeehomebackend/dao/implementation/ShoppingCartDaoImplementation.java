package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ShoppingCartDao;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCart;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartDto;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartProduct;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ShoppingCartDaoImplementation implements ShoppingCartDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public ShoppingCart getShoppingCart(String userId) {
        String sql = "SELECT p.shop_id, s.name AS shop_name, sc.product_id, p.name AS product_name, i.image, sc.quantity, sc.quantity_limit, p.price, p.discount_rate, p.discount_date " +
                "FROM shop s " +
                "JOIN product p ON s.id = p.shop_id " +
                "JOIN product_image i ON p.id = i.product_id " +
                "JOIN in_shopping_cart sc ON sc.product_id = p.id " +
                "WHERE sc.user_id = :user_id AND i.image_order = 1 ORDER BY s.name ASC;";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        List<ShoppingCartDto> shoppingCartDtos = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            ShoppingCartDto shoppingCartDto = new ShoppingCartDto(
                    rs.getString("shop_id"),
                    rs.getString("shop_name"),
                    rs.getString("product_id"),
                    rs.getString("product_name"),
                    rs.getString("image"),
                    rs.getInt("quantity"),
                    rs.getInt("quantity_limit"),
                    rs.getInt("price"),
                    rs.getDouble("discount_rate"),
                    rs.getDate("discount_date"));
            return shoppingCartDto;
        });
        if (shoppingCartDtos.isEmpty()) {
            return null;
        }

        Map<String, ShoppingCartShop> shopMap = new HashMap<>();

        for (ShoppingCartDto shoppingCartDto : shoppingCartDtos) {
            String shopId = shoppingCartDto.getShopId();

            // If the shop is not in the map, add it
            if (!shopMap.containsKey(shopId)) {
                shopMap.put(shopId, new ShoppingCartShop(
                        shopId,
                        shoppingCartDto.getShopName(),
                        new ArrayList<>()
                ));
            }

            // calculate the price of single product after discount
            int price;
            if (shoppingCartDto.getDiscountDate() != null && !shoppingCartDto.getDiscountDate().before(new Date(System.currentTimeMillis()))) {
                price = (int) (shoppingCartDto.getPrice() * shoppingCartDto.getDiscountRate());
            } else {
                price = shoppingCartDto.getPrice();
            }


            ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct(
                    shoppingCartDto.getProductId(),
                    shoppingCartDto.getProductName(),
                    shoppingCartDto.getProductImage(),
                    shoppingCartDto.getQuantity(),
                    shoppingCartDto.getQuantityLimit(),
                    price
            );

            shopMap.get(shopId).getProducts().add(shoppingCartProduct);
        }

        List<ShoppingCartShop> sortedShopList = new ArrayList<>(shopMap.values());
        sortedShopList.sort(Comparator.comparing(ShoppingCartShop::getName));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setShops(sortedShopList);
        return shoppingCart;
    }

    @Override
    public void updateShoppingCart(String userId, ShoppingCart shoppingCart) {
        String deleteSql = "DELETE FROM in_shopping_cart WHERE user_id = :user_id;";
        Map<String, Object> deleteMap = new HashMap<>();
        deleteMap.put("user_id", userId);
        jdbcTemplate.update(deleteSql, deleteMap);

        String sql = "INSERT INTO in_shopping_cart (user_id, product_id, quantity) VALUES (:user_id, :product_id, :quantity);";
        Map<String, Object> map;
        for (ShoppingCartShop shop : shoppingCart.getShops()) {
            for (ShoppingCartProduct product : shop.getProducts()) {
                map = new HashMap<>();
                map.put("user_id", userId);
                map.put("product_id", product.getId());
                map.put("quantity", product.getQuantity());
                jdbcTemplate.update(sql, map);
            }
        }
    }

}
