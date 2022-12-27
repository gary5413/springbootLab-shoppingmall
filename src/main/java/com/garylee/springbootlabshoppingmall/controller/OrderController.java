package com.garylee.springbootlabshoppingmall.controller;

import com.garylee.springbootlabshoppingmall.dto.CreateOrderRequest;
import com.garylee.springbootlabshoppingmall.dto.OrderQueryParams;
import com.garylee.springbootlabshoppingmall.model.Order;
import com.garylee.springbootlabshoppingmall.service.OrderService;
import com.garylee.springbootlabshoppingmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

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
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
                                                 @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        OrderQueryParams orderQueryParams=new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);
//        取得 order list
        List<Order> orderList=orderService.getOrders(orderQueryParams);
//        取得 order 總數
        Integer count =orderService.countOrder(orderQueryParams);
        Page<Order> page=new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
