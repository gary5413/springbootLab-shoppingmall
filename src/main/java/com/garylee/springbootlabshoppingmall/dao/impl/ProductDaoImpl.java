package com.garylee.springbootlabshoppingmall.dao.impl;

import com.garylee.springbootlabshoppingmall.dao.ProductDao;
import com.garylee.springbootlabshoppingmall.model.Product;
import com.garylee.springbootlabshoppingmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

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
}
