package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.dto.order.OrderCreateDto;
import com.calvinwan.shopeehomebackend.model.order.Order;
import com.calvinwan.shopeehomebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/order_id/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable String orderId) {
        Order order = orderService.getOrderByOrderId(orderId);
        if (order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/order/user_id/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable String userId) {
        List<Order> orders = orderService.getOrderByUserId(userId);
        if (orders != null) {
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/order/shop_id/{shopId}")
    public ResponseEntity<List<Order>> getOrderByShopId(@PathVariable String shopId) {
        List<Order> orders = orderService.getOrderByShopId(shopId);
        if (orders != null) {
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        String id = orderService.createOrder(orderCreateDto);
        Order order = orderService.getOrderByOrderId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/order/ship/{orderId}")
    public ResponseEntity<Order> shopShipProduct(@PathVariable String orderId) {
        orderService.shopShipProduct(orderId);
        Order order = orderService.getOrderByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PutMapping("/order/receive/{orderId}")
    public ResponseEntity<Order> userReceiveProduct(@PathVariable String orderId) {
        orderService.userReceiveProduct(orderId);
        Order order = orderService.getOrderByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

}
