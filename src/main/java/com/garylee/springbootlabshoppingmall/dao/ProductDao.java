package com.garylee.springbootlabshoppingmall.dao;

import com.garylee.springbootlabshoppingmall.dto.ProductRequest;
import com.garylee.springbootlabshoppingmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
