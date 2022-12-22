package com.garylee.springbootlabshoppingmall.dto;

import com.garylee.springbootlabshoppingmall.constant.ProductCategory;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/*
dto : data transfer object
此類別用來判斷或檢查 新增產品時 前端傳來的值
1. 複製產品類別的變數
2. 決定前端傳來的變數
    - 自動生成 不用
        private Integer productId;
    - springboot 設定 也不用時間設定
        private Date createdDate;
        private Date lastModifiedDate;
3. @NotNull註解
   - pom.xml 要加入套件
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
 */
@Data
public class ProductRequest {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer price;
    @NotNull
    private Integer stock;
    private String description;

}
