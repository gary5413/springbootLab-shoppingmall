package com.garylee.springbootlabshoppingmall.model;


import com.garylee.springbootlabshoppingmall.constant.ProductCategory;
import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Integer productId;
    private String productName;
//    private String category;
//  我們的分類是固定的且先定義好的 使用String類型 可能會又增加錯誤或非定義好的值  程式也會提升可讀性
    private ProductCategory category;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
//    Date類型預設使用 GMT+0
    private Date createdDate;
    private Date lastModifiedDate;
}
