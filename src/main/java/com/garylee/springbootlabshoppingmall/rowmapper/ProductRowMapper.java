package com.garylee.springbootlabshoppingmall.rowmapper;

import com.garylee.springbootlabshoppingmall.constant.ProductCategory;
import com.garylee.springbootlabshoppingmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
//        product.setCategory(rs.getString("category"));
//      因為類別變數改變類型 從 String > Enum 也要改寫方式
//        String categoryStr=rs.getString("category");
//        ProductCategory category = ProductCategory.valueOf(categoryStr);
//        product.setCategory(category);
//      也可以寫成一行
        product.setCategory(ProductCategory.valueOf(rs.getString("category")));
        product.setImageUrl(rs.getString("image_url"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));
        product.setDescription(rs.getString("description"));
        product.setCreatedDate(rs.getTimestamp("created_date"));
        product.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
        return product;
    }
}
