package com.garylee.springbootlabshoppingmall.service.impl;

import com.garylee.springbootlabshoppingmall.dao.ProductDao;
import com.garylee.springbootlabshoppingmall.model.Product;
import com.garylee.springbootlabshoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
