package com.garylee.springbootlabshoppingmall.service;

import com.garylee.springbootlabshoppingmall.dto.ProductRequest;
import com.garylee.springbootlabshoppingmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
