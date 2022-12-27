package com.garylee.springbootlabshoppingmall.controller;

import com.garylee.springbootlabshoppingmall.dto.CreateOrderRequest;
import com.garylee.springbootlabshoppingmall.model.Order;
import com.garylee.springbootlabshoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Validated CreateOrderRequest createOrderRequest){
        Integer orderId=orderService.createOrder(userId,createOrderRequest);
        Order order=orderService.getOrderById(orderId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
