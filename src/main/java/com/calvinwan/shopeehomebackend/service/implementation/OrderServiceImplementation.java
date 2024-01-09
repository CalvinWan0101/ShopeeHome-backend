package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dao.OrderDao;
import com.calvinwan.shopeehomebackend.dto.order.OrderCreateDto;
import com.calvinwan.shopeehomebackend.model.order.Order;
import com.calvinwan.shopeehomebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderDao.getOrderByOrderId(orderId);
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public List<Order> getOrderByShopId(String shopId) {
        return orderDao.getOrderByShopId(shopId);
    }

    @Override
    public String createOrder(OrderCreateDto orderCreateDto) {
        return orderDao.createOrder(orderCreateDto);
    }

    @Override
    public void shopShipProduct(String orderId) {
        orderDao.shopShipProduct(orderId);
    }

    @Override
    public void userReceiveProduct(String orderId) {
        orderDao.userReceiveProduct(orderId);
    }
}
