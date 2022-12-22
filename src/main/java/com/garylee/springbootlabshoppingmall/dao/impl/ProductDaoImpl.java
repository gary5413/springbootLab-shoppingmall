package com.garylee.springbootlabshoppingmall.dao.impl;

import com.garylee.springbootlabshoppingmall.dao.ProductDao;
import com.garylee.springbootlabshoppingmall.dto.ProductRequest;
import com.garylee.springbootlabshoppingmall.model.Product;
import com.garylee.springbootlabshoppingmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Spring Data JDBC寫法
要注入NamedParameterJdbcTemplate
 */
@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Product getProductById(Integer productId) {
//        注意:productId 是參數 而非欄位
        String sql="SELECT product_id,product_name, category, image_url, price, stock, description, created_date, " +
                "last_modified_date FROM product WHERE product_id = :productId";
        Map<String,Object> map=new HashMap<>();
        map.put("productId",productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if(productList.size()>0){
            return productList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql="INSERT INTO product (product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date) " +
                "VALUES (:productName, :category,:imageUrl,:price,:stock,:description,:createdDate,:lastModifiedDate)";
        HashMap<String, Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
//        這裡要toString()
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
//       紀錄當下時間 再設定進去
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
//        儲存資料自動生成的product id
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql="UPDATE product SET product_name = :productName,category = :category,image_url= :imageUrl," +
                "price= :price,stock = :stock,description= :description, last_modified_date= :lastModifiedDate" +
                " WHERE product_id = :productId";
        HashMap<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("lastModifiedDate",new Date());
        namedParameterJdbcTemplate.update(sql,map);
    }
}
