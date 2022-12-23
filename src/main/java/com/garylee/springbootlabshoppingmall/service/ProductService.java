package com.garylee.springbootlabshoppingmall.service;

import com.garylee.springbootlabshoppingmall.constant.ProductCategory;
import com.garylee.springbootlabshoppingmall.dto.ProductQueryParams;
import com.garylee.springbootlabshoppingmall.dto.ProductRequest;
import com.garylee.springbootlabshoppingmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductQueryParams productQueryParams);
//    List<Product> getProducts(ProductCategory category,String search);
    Integer countProduct(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProductById(Integer productId);
    }
