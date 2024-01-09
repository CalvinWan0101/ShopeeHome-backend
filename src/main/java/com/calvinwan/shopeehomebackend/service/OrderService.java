package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.order.OrderCreateDto;
import com.calvinwan.shopeehomebackend.model.order.Order;

import java.util.List;

public interface OrderService {
    Order getOrderByOrderId(String orderId);

    List<Order> getOrderByUserId(String userId);

    List<Order> getOrderByShopId(String shopId);

    String createOrder(OrderCreateDto orderCreateDto);

    void shopShipProduct(String orderId);

    void userReceiveProduct(String orderId);
}
