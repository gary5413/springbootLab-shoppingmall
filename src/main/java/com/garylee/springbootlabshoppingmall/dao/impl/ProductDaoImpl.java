package com.garylee.springbootlabshoppingmall.dao.impl;

import com.garylee.springbootlabshoppingmall.constant.ProductCategory;
import com.garylee.springbootlabshoppingmall.dao.ProductDao;
import com.garylee.springbootlabshoppingmall.dto.ProductQueryParams;
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
    /*
        1. sql 加入 WHERE 1=1 對查詢結果不會有任何影響 因為想要讓查詢條件自由的拼接
            加入 AND 可以自由加入條件 注意AND前面要空格
            WHERE 1=1 AND category = :category;
        2. 此為Spring JDBC 寫法
        3. Spring Data JPA or Hiberante 會幫你處理
        4. % + xxx + % 要加在 map那邊
     */
//    public List<Product> getProducts(ProductCategory category,String search) {
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql="SELECT product_id,product_name, category, image_url, price, stock, description, created_date," +
                " last_modified_date FROM product WHERE 1=1";
        HashMap<String, Object> map = new HashMap<>();
//       查詢條件
//       提煉程式
        sql=addFilteringSql(sql,map,productQueryParams);
//        if(category !=null){
//        if(productQueryParams.getCategory() !=null){
//            sql = sql+" AND category = :category";
////            map.put("category",category.name());
//            map.put("category",productQueryParams.getCategory().name());
//        }
////        if(search !=null){
//        if(productQueryParams.getSearch() !=null){
//            sql =sql +" AND product_name LIKE :search";
////            map.put("search","%"+search+"%");
//            map.put("search","%"+productQueryParams.getSearch()+"%");
//        }
//        排序
        sql =sql + " ORDER BY "+productQueryParams.getOrderBy() +" "+productQueryParams.getSort();
//        分頁
        sql =sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffset());
        List<Product> productList=namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());
        return productList;
    }

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql="SELECT count(*) FROM product WHERE 1=1";
        HashMap<String, Object> map = new HashMap<>();
//        查詢條件
//        提煉程式
        sql=addFilteringSql(sql,map,productQueryParams);
//        if(productQueryParams.getCategory() !=null){
//            sql = sql+" AND category = :category";
//            map.put("category",productQueryParams.getCategory().name());
//        }
//        if(productQueryParams.getSearch() !=null){
//            sql =sql +" AND product_name LIKE :search";
//            map.put("search","%"+productQueryParams.getSearch()+"%");
//        }
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }
//    最一開始的寫法
//    @Override
//    public List<Product> getProducts() {
//        String sql="SELECT product_id,product_name, category, image_url, price, stock, description, created_date," +
//                " last_modified_date FROM product";
//        HashMap<String, Object> map = new HashMap<>();
//        List<Product> productList=namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());
//        return productList;
//    }

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

    @Override
    public void deleteProductById(Integer productId) {
        String sql="DELETE FROM product WHERE product_id =:productId";
        HashMap<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }
    private String addFilteringSql(String sql,Map<String,Object> map,ProductQueryParams productQueryParams ){
        if(productQueryParams.getCategory() !=null){
            sql = sql+" AND category = :category";
            map.put("category",productQueryParams.getCategory().name());
        }
        if(productQueryParams.getSearch() !=null){
            sql =sql +" AND product_name LIKE :search";
            map.put("search","%"+productQueryParams.getSearch()+"%");
        }
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return sql;
    }
}
