package com.garylee.springbootlabshoppingmall.dto;

import com.garylee.springbootlabshoppingmall.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductQueryParams {
    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sort;
}
