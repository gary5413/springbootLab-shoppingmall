package com.garylee.springbootlabshoppingmall.dto;

import com.garylee.springbootlabshoppingmall.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductQueryParams {
    ProductCategory category;
    String search;
}
