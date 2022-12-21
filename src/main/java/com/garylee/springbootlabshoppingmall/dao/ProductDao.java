package com.garylee.springbootlabshoppingmall.dao;

import com.garylee.springbootlabshoppingmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
