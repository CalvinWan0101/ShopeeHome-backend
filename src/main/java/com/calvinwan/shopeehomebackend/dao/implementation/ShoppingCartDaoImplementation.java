//package com.calvinwan.shopeehomebackend.dao.implementation;
//
//import com.calvinwan.shopeehomebackend.dao.ShoppingCartDao;
//import com.calvinwan.shopeehomebackend.dto.shopping_cart.Refresh;
//import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCart;
//import com.calvinwan.shopeehomebackend.dto.shopping_cart.ShoppingCartDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.fasterxml.jackson.databind.type.LogicalType.Map;
//
//@Component
//public class ShoppingCartDaoImplementation implements ShoppingCartDao {
//    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Override
//    public ShoppingCart getShoppingCart(String userId) {
//        String sql = "SELECT p.shop_id, s.name, sc.product_id, p.name, i.image, sc.quantity, p.discount_rate, p.dicount_date" +
//                "FROM shop s" +
//                "JOIN product p ON s.id = p.shop_id" +
//                "JOIN product_image i ON p.id = i.product_id" +
//                "JOIN in_shopping_cart sc ON sc.product_id = p.id" +
//                "WHERE sc.user_id = :user_id AND i.image_order = 1 ORDER BY P.SHOP_ID ASC;";
//        Map<String, Object> map = new HashMap<>();
//        map.put("user_id", userId);
//        List<ShoppingCartDto> shoppingCarts = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
//            ShoppingCartDto shoppingCartDto = new ShoppingCartDto(
//                    rs.getString("shop_id"),
//                    rs.getString("name"),
//                    rs.getString("product_id"),
//                    rs.getString("name"),
//                    rs.getString("image"),
//                    rs.getInt("quantity"),
//                    rs.getDouble("discount_rate"),
//                    rs.getDate("discount_date"));
//            return shoppingCartDto;
//        });
//        if (shoppingCarts.isEmpty()) {
//            return null;
//        }
//    }
//
//}
//
//@Override
//public Refresh updateShoppingCart(String userId, ShoppingCart shoppingCart) {
//    return null;
//}
//}
