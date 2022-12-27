package com.garylee.springbootlabshoppingmall.service;

import com.garylee.springbootlabshoppingmall.dto.CreateOrderRequest;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
