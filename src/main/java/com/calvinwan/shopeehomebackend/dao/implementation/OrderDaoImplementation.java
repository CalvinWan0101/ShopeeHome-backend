package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.OrderDao;
import com.calvinwan.shopeehomebackend.dto.order.OrderCreateDto;
import com.calvinwan.shopeehomebackend.dto.order.OrderDto;
import com.calvinwan.shopeehomebackend.dto.order.OrderProductDto;
import com.calvinwan.shopeehomebackend.dto.order.ProductPriceDto;
import com.calvinwan.shopeehomebackend.model.order.Order;
import com.calvinwan.shopeehomebackend.model.order.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderDaoImplementation implements OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Order getOrderByOrderId(String orderId) {
        String sql = "SELECT o.id, " +
                "o.user_id, " +
                "ouc.coupon_id, " +
                "o.address, " +
                "o.total_price, " +
                "o.shipping_cost, " +
                "sec.rate, " +
                "shc.shipping_limit, " +
                "o.start_time, " +
                "o.deliver_time, " +
                "oip.product_id, " +
                "p.name, " +
                "oip.price, " +
                "oip.quantity, " +
                "pi.image " +
                "FROM myorder o " +
                "LEFT JOIN order_use_coupon ouc ON o.id = ouc.order_id " +
                "LEFT JOIN seasoning_coupon sec ON ouc.coupon_id = sec.coupon_id " +
                "LEFT JOIN shipping_coupon shc ON ouc.coupon_id = shc.coupon_id " +
                "LEFT JOIN order_include_product oip ON o.id = oip.order_id " +
                "LEFT JOIN product p ON oip.product_id = p.id " +
                "LEFT JOIN product_image pi ON oip.product_id = pi.product_id " +
                "WHERE o.id = :order_id " +
                "AND pi.image_order = 1;";

        Map<String, Object> map = new HashMap<>();
        map.put("order_id", orderId);
        List<OrderDto> orderDtos = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            OrderDto orderDto = new OrderDto(
                    rs.getString("id"),
                    rs.getString("user_id"),
                    rs.getString("coupon_id"),
                    rs.getString("address"),
                    rs.getInt("total_price"),
                    rs.getInt("shipping_cost"),
                    rs.getDouble("rate"),
                    rs.getInt("shipping_limit"),
                    rs.getDate("start_time"),
                    rs.getDate("deliver_time"),
                    rs.getString("product_id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getInt("quantity"),
                    rs.getString("image")
            );
            return orderDto;
        });
        if (orderDtos.isEmpty()) {
            return null;
        }

        // build product list
        List<OrderProduct> products = new ArrayList<>();
        for (OrderDto orderDto : orderDtos) {
            OrderProduct orderProduct = new OrderProduct(
                    orderDto.getProductId(),
                    orderDto.getProductName(),
                    orderDto.getProductPrice(),
                    orderDto.getProductQuantity(),
                    orderDto.getProductImage()
            );
            products.add(orderProduct);
        }

        Order order = new Order(
                orderDtos.get(0).getId(),
                orderDtos.get(0).getUserId(),
                orderDtos.get(0).getCouponId(),
                orderDtos.get(0).getAddress(),
                orderDtos.get(0).getTotalPrice(),
                orderDtos.get(0).getShippingCost(),
                orderDtos.get(0).getRate(),
                orderDtos.get(0).getShippingLimit(),
                orderDtos.get(0).getStartTime(),
                orderDtos.get(0).getDeliverTime(),
                products
        );
        return order;
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        String sql = "SELECT o.id, " +
                "o.user_id, " +
                "ouc.coupon_id, " +
                "o.address, " +
                "o.total_price, " +
                "o.shipping_cost, " +
                "sec.rate, " +
                "shc.shipping_limit, " +
                "o.start_time, " +
                "o.deliver_time, " +
                "oip.product_id, " +
                "p.name, " +
                "oip.price, " +
                "oip.quantity, " +
                "pi.image " +
                "FROM myorder o " +
                "LEFT JOIN order_use_coupon ouc ON o.id = ouc.order_id " +
                "LEFT JOIN seasoning_coupon sec ON ouc.coupon_id = sec.coupon_id " +
                "LEFT JOIN shipping_coupon shc ON ouc.coupon_id = shc.coupon_id " +
                "LEFT JOIN order_include_product oip ON o.id = oip.order_id " +
                "LEFT JOIN product p ON oip.product_id = p.id " +
                "LEFT JOIN product_image pi ON oip.product_id = pi.product_id " +
                "WHERE o.user_id = :user_id " +
                "AND pi.image_order = 1;";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);

        List<OrderDto> orderDtos = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            OrderDto orderDto = new OrderDto(
                    rs.getString("id"),
                    rs.getString("user_id"),
                    rs.getString("coupon_id"),
                    rs.getString("address"),
                    rs.getInt("total_price"),
                    rs.getInt("shipping_cost"),
                    rs.getDouble("rate"),
                    rs.getInt("shipping_limit"),
                    rs.getDate("start_time"),
                    rs.getDate("deliver_time"),
                    rs.getString("product_id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getInt("quantity"),
                    rs.getString("image")
            );
            return orderDto;
        });

        if (orderDtos.isEmpty()) {
            return null;
        }

        Map<String, Order> orderMap = new HashMap<>();
        for (OrderDto orderDto : orderDtos) {
            // if the order is not in the map, add it
            if (!orderMap.containsKey(orderDto.getId())) {
                orderMap.put(orderDto.getId(), new Order(
                        orderDto.getId(),
                        orderDto.getUserId(),
                        orderDto.getCouponId(),
                        orderDto.getAddress(),
                        orderDto.getTotalPrice(),
                        orderDto.getShippingCost(),
                        orderDto.getRate(),
                        orderDto.getShippingLimit(),
                        orderDto.getStartTime(),
                        orderDto.getDeliverTime(),
                        new ArrayList<>()
                ));
            }

            // build product list
            OrderProduct orderProduct = new OrderProduct(
                    orderDto.getProductId(),
                    orderDto.getProductName(),
                    orderDto.getProductPrice(),
                    orderDto.getProductQuantity(),
                    orderDto.getProductImage()
            );
            orderMap.get(orderDto.getId()).getProducts().add(orderProduct);
        }
        List<Order> orders = new ArrayList<>(orderMap.values());
        orders.sort(Comparator.comparing(Order::getStartTime, Comparator.nullsFirst(Comparator.reverseOrder())));
        return orders;
    }

    @Override
    public List<Order> getOrderByShopId(String shopId) {
        String sql = "SELECT o.id, " +
                "o.user_id, " +
                "ouc.coupon_id, " +
                "o.address, " +
                "o.total_price, " +
                "o.shipping_cost, " +
                "sec.rate, " +
                "shc.shipping_limit, " +
                "o.start_time, " +
                "o.deliver_time, " +
                "oip.product_id, " +
                "p.name, " +
                "oip.price, " +
                "oip.quantity, " +
                "pi.image " +
                "FROM myorder o " +
                "LEFT JOIN order_use_coupon ouc ON o.id = ouc.order_id " +
                "LEFT JOIN seasoning_coupon sec ON ouc.coupon_id = sec.coupon_id " +
                "LEFT JOIN shipping_coupon shc ON ouc.coupon_id = shc.coupon_id " +
                "LEFT JOIN order_include_product oip ON o.id = oip.order_id " +
                "LEFT JOIN product p ON oip.product_id = p.id " +
                "LEFT JOIN product_image pi ON oip.product_id = pi.product_id " +
                "WHERE p.shop_id = :shop_id " +
                "AND pi.image_order = 1;";
        Map<String, Object> map = new HashMap<>();
        map.put("shop_id", shopId);

        List<OrderDto> orderDtos = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            OrderDto orderDto = new OrderDto(
                    rs.getString("id"),
                    rs.getString("user_id"),
                    rs.getString("coupon_id"),
                    rs.getString("address"),
                    rs.getInt("total_price"),
                    rs.getInt("shipping_cost"),
                    rs.getDouble("rate"),
                    rs.getInt("shipping_limit"),
                    rs.getDate("start_time"),
                    rs.getDate("deliver_time"),
                    rs.getString("product_id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getInt("quantity"),
                    rs.getString("image")
            );
            return orderDto;
        });

        if (orderDtos.isEmpty()) {
            return null;
        }

        Map<String, Order> orderMap = new HashMap<>();
        for (OrderDto orderDto : orderDtos) {
            // if the order is not in the map, add it
            if (!orderMap.containsKey(orderDto.getId())) {
                orderMap.put(orderDto.getId(), new Order(
                        orderDto.getId(),
                        orderDto.getUserId(),
                        orderDto.getCouponId(),
                        orderDto.getAddress(),
                        orderDto.getTotalPrice(),
                        orderDto.getShippingCost(),
                        orderDto.getRate(),
                        orderDto.getShippingLimit(),
                        orderDto.getStartTime(),
                        orderDto.getDeliverTime(),
                        new ArrayList<>()
                ));
            }

            // build product list
            OrderProduct orderProduct = new OrderProduct(
                    orderDto.getProductId(),
                    orderDto.getProductName(),
                    orderDto.getProductPrice(),
                    orderDto.getProductQuantity(),
                    orderDto.getProductImage()
            );
            orderMap.get(orderDto.getId()).getProducts().add(orderProduct);
        }
        List<Order> orders = new ArrayList<>(orderMap.values());
        orders.sort(Comparator.comparing(Order::getStartTime, Comparator.nullsFirst(Comparator.reverseOrder())));
        return orders;
    }

    @Override
    public String createOrder(OrderCreateDto orderCreateDto) {
        String orderId = UUID.randomUUID().toString();

        int totalPrice = 0;
        int shippingCost = 0;

        for (OrderProductDto orderProductDto : orderCreateDto.getProducts()) {
            totalPrice += getPrice(orderProductDto.getId()) * orderProductDto.getQuantity();
        }

        if (isSeasoningCoupon(orderCreateDto.getCouponId())) {
            totalPrice = (int) (totalPrice * getDiscountRate(orderCreateDto.getCouponId()));
            shippingCost = 60;
            totalPrice += 60;
        } else if (isShippingCoupon(orderCreateDto.getCouponId())) {
            if (totalPrice < getShippingLimit(orderCreateDto.getCouponId())) {
                shippingCost = 0;
            }
        } else {
            shippingCost = 60;
            totalPrice += 60;
        }

        String sql = "INSERT INTO myorder (id, user_id, address, total_price, shipping_cost) VALUES (:id, :user_id, :address, :total_price, :shipping_cost);";
        Map<String, Object> map = new HashMap<>();
        map.put("id", orderId);
        map.put("user_id", orderCreateDto.getUserId());
        map.put("address", orderCreateDto.getAddress());
        map.put("total_price", totalPrice);
        map.put("shipping_cost", shippingCost);
        jdbcTemplate.update(sql, map);

        if (orderCreateDto.getCouponId() != null) {
            sql = "INSERT INTO order_use_coupon (order_id, coupon_id) VALUES (:order_id, :coupon_id);";
            map = new HashMap<>();
            map.put("order_id", orderId);
            map.put("coupon_id", orderCreateDto.getCouponId());
            jdbcTemplate.update(sql, map);
        }

        for (OrderProductDto orderProductDto : orderCreateDto.getProducts()) {
            sql = "INSERT INTO order_include_product (order_id, product_id, price, quantity) VALUES (:order_id, :product_id, :price, :quantity);";
            map = new HashMap<>();
            map.put("order_id", orderId);
            map.put("product_id", orderProductDto.getId());
            map.put("price", getPrice(orderProductDto.getId()));
            map.put("quantity", orderProductDto.getQuantity());
            jdbcTemplate.update(sql, map);
        }

        sql = "UPDATE user_has_coupon SET is_used = true WHERE user_id = :user_id AND coupon_id = :coupon_id;";
        map = new HashMap<>();
        map.put("user_id", orderCreateDto.getUserId());
        map.put("coupon_id", orderCreateDto.getCouponId());
        jdbcTemplate.update(sql, map);

        for (OrderProductDto orderProductDto : orderCreateDto.getProducts()) {
            sql = "UPDATE product SET amount = amount - :quantity WHERE id = :product_id;";
            map = new HashMap<>();
            map.put("quantity", orderProductDto.getQuantity());
            map.put("product_id", orderProductDto.getId());
            jdbcTemplate.update(sql, map);

            sql = "UPDATE product SET sales = sales + :quantity WHERE id = :product_id;";
            map = new HashMap<>();
            map.put("quantity", orderProductDto.getQuantity());
            map.put("product_id", orderProductDto.getId());
            jdbcTemplate.update(sql, map);

            sql = "DELETE FROM in_shopping_cart WHERE user_id = :user_id AND product_id = :product_id;";
            map = new HashMap<>();
            map.put("user_id", orderCreateDto.getUserId());
            map.put("product_id", orderProductDto.getId());
            jdbcTemplate.update(sql, map);
        }
        return orderId;
    }

    private int getPrice(String productId) {
        String sql = "SELECT price, discount_rate, discount_date FROM product WHERE id = :product_id;";
        Map<String, Object> map = new HashMap<>();
        map.put("product_id", productId);
        List<ProductPriceDto> productPriceDtos = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            ProductPriceDto productPriceDto = new ProductPriceDto(
                    rs.getInt("price"),
                    rs.getDouble("discount_rate"),
                    rs.getDate("discount_date")
            );
            return productPriceDto;
        });
        if (productPriceDtos.isEmpty()) {
            return 0;
        }
        // calculate the price of single product after discount
        int price;
        if (productPriceDtos.get(0).getDiscountDate() != null && !productPriceDtos.get(0).getDiscountDate().before(new Date(System.currentTimeMillis()))) {
            price = (int) (productPriceDtos.get(0).getPrice() * productPriceDtos.get(0).getDiscountRate());
        } else {
            price = productPriceDtos.get(0).getPrice();
        }
        return price;
    }

    private boolean isSeasoningCoupon(String couponId) throws DataAccessException {
        String sql = "SELECT COUNT(*) AS count FROM seasoning_coupon WHERE coupon_id = :coupon_id;";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("coupon_id", couponId);

        int count = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return count > 0;
    }


    private Double getDiscountRate(String id) {
        String sql = "SELECT rate FROM seasoning_coupon WHERE coupon_id = :coupon_id;";
        Map<String, Object> map = new HashMap<>();
        map.put("coupon_id", id);
        List<Double> discountRates = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            Double discountRate = rs.getDouble("rate");
            return discountRate;
        });
        if (discountRates.isEmpty()) {
            return 0.0;
        }
        return discountRates.get(0);
    }

    private boolean isShippingCoupon(String id) throws DataAccessException {
        String sql = "SELECT COUNT(*) AS count FROM shipping_coupon WHERE coupon_id = :coupon_id;";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("coupon_id", id);

        int count = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return count > 0;
    }

    private int getShippingLimit(String id) {
        String sql = "SELECT shipping_limit FROM shipping_coupon WHERE coupon_id = :coupon_id;";
        Map<String, Object> map = new HashMap<>();
        map.put("coupon_id", id);
        List<Integer> shippingLimits = jdbcTemplate.query(sql, map, (rs, rowNum) -> {
            Integer shippingLimit = rs.getInt("shipping_limit");
            return shippingLimit;
        });
        if (shippingLimits.isEmpty()) {
            return 0;
        }
        return shippingLimits.get(0);
    }

    @Override
    public void shopShipProduct(String orderId) {
        String sql = "UPDATE myorder SET start_time = :start_time WHERE id = :order_id;";
        Map<String, Object> map = new HashMap<>();
        map.put("start_time", new Date(System.currentTimeMillis()));
        map.put("order_id", orderId);
        jdbcTemplate.update(sql, map);
    }

    @Override
    public void userReceiveProduct(String orderId) {
        String sql = "UPDATE myorder SET deliver_time = :deliver_time WHERE id = :order_id;";
        Map<String, Object> map = new HashMap<>();
        map.put("deliver_time", new Date(System.currentTimeMillis()));
        map.put("order_id", orderId);
        jdbcTemplate.update(sql, map);
    }
}
