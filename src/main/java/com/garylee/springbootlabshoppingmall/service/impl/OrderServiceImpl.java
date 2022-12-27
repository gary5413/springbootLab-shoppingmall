package com.garylee.springbootlabshoppingmall.service.impl;

import com.garylee.springbootlabshoppingmall.dao.OrderDao;
import com.garylee.springbootlabshoppingmall.dao.ProductDao;
import com.garylee.springbootlabshoppingmall.dto.BuyItem;
import com.garylee.springbootlabshoppingmall.dto.CreateOrderRequest;
import com.garylee.springbootlabshoppingmall.model.OrderItem;
import com.garylee.springbootlabshoppingmall.model.Product;
import com.garylee.springbootlabshoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
//      計算總價錢
        int totalAmount=0;
        List<OrderItem> orderItemList=new ArrayList<>();
        for(BuyItem buyItem:createOrderRequest.getBuyItemList()){
            Product product=productDao.getProductById(buyItem.getProductId());
            int amount =buyItem.getQuantity() * product.getPrice();
            totalAmount=totalAmount+amount;
//           轉換BuyItem to OrderItem
            OrderItem orderItem=new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        //        創建訂單
        Integer orderId=orderDao.createOrder(userId,totalAmount);

        orderDao.createOrderItems(orderId,orderItemList);
        return orderId;
    }
}
