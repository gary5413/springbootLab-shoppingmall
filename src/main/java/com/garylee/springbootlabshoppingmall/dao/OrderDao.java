package com.garylee.springbootlabshoppingmall.dao;

import com.garylee.springbootlabshoppingmall.model.Order;
import com.garylee.springbootlabshoppingmall.model.OrderItem;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId,Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}
