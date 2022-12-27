package com.garylee.springbootlabshoppingmall.service;

import com.garylee.springbootlabshoppingmall.dto.CreateOrderRequest;
import com.garylee.springbootlabshoppingmall.dto.OrderQueryParams;
import com.garylee.springbootlabshoppingmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
}
