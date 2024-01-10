package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ShoppingCartDao;
import com.calvinwan.shopeehomebackend.dto.shopping_cart.*;
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
        String sql = "SELECT p.shop_id, s.name AS shop_name, sc.product_id, p.name AS product_name, i.image, sc.quantity, p.amount AS quantity_limit, p.price, p.discount_rate, p.discount_date " +
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

            // if the quantity in the shopping cart is lower or equal to zero, delete the product from the shopping cart
            if (shoppingCartDto.getQuantity() <= 0 || shoppingCartDto.getQuantityLimit() <= 0) {
                String deleteSql = "DELETE FROM in_shopping_cart WHERE user_id = :user_id AND product_id = :product_id;";
                Map<String, Object> deleteMap = new HashMap<>();
                deleteMap.put("user_id", userId);
                deleteMap.put("product_id", shoppingCartDto.getProductId());
                jdbcTemplate.update(deleteSql, deleteMap);
                continue;
            }

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

    @Override
    public void addProductToShoppingCart(ShoppingCartSingleProduct shoppingCartSingleProduct) {
        // check if the product is already in the shopping cart
        String checkSql = "SELECT * FROM in_shopping_cart WHERE user_id = :user_id AND product_id = :product_id;";
        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("user_id", shoppingCartSingleProduct.getUserId());
        checkMap.put("product_id", shoppingCartSingleProduct.getProductId());
        List<ShoppingCartSingleProduct> shoppingCartSingleProducts = jdbcTemplate.query(checkSql, checkMap, (rs, rowNum) -> {
            ShoppingCartSingleProduct shoppingCartSingleProduct1 = new ShoppingCartSingleProduct(
                    rs.getString("user_id"),
                    rs.getString("product_id"),
                    rs.getInt("quantity")
            );
            return shoppingCartSingleProduct1;
        });

        // if the product is already in the shopping cart, update the quantity
        if (!shoppingCartSingleProducts.isEmpty()) {
            String updateSql = "UPDATE in_shopping_cart SET quantity = :quantity WHERE user_id = :user_id AND product_id = :product_id;";
            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("user_id", shoppingCartSingleProduct.getUserId());
            updateMap.put("product_id", shoppingCartSingleProduct.getProductId());
            updateMap.put("quantity", shoppingCartSingleProduct.getQuantity() + shoppingCartSingleProducts.get(0).getQuantity());
            jdbcTemplate.update(updateSql, updateMap);
        } else {
            String sql = "INSERT INTO in_shopping_cart (user_id, product_id, quantity) VALUES (:user_id, :product_id, :quantity);";
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", shoppingCartSingleProduct.getUserId());
            map.put("product_id", shoppingCartSingleProduct.getProductId());
            map.put("quantity", shoppingCartSingleProduct.getQuantity());
            jdbcTemplate.update(sql, map);
        }
    }

}
