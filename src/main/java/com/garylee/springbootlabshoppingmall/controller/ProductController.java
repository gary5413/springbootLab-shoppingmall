package com.garylee.springbootlabshoppingmall.controller;

import com.garylee.springbootlabshoppingmall.constant.ProductCategory;
import com.garylee.springbootlabshoppingmall.dto.ProductQueryParams;
import com.garylee.springbootlabshoppingmall.dto.ProductRequest;
import com.garylee.springbootlabshoppingmall.model.Product;
import com.garylee.springbootlabshoppingmall.service.ProductService;
import com.garylee.springbootlabshoppingmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    /*
      查詢商品功能
        1. 查詢商品全部
        2. 查詢條件商品
            @RequestParam(required = false) ProductCategory category 非必需參數
        3. 加強改善傳遞參數 另外創一個類別來控管傳遞參數
        4. 新增排序 orderBy 通常預設會是最新商品 (defaultValue = "created_date")
            - sort 升序 降序
        5. 新增分頁
            - limit - offset
            @Max @Min 要再類別加上@Validated 才會生效
        6. 回傳List 改回傳 JSON
     */
    @Validated
    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getProducts(
    public ResponseEntity<Page<Product>> getProducts(
//          查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
//           排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
//           分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);
        List<Product> productList=productService.getProducts(productQueryParams);
//      取得總筆數
        Integer total =productService.countProduct(productQueryParams);
//      分頁的另一種寫法
        Page<Product> page=new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);
//        return ResponseEntity.status(HttpStatus.OK).body(productList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getProducts(
//            @RequestParam(required = false) ProductCategory category,
//            @RequestParam(required = false) String search
//    ){
//        List<Product> productList=productService.getProducts(category,search);
//        return ResponseEntity.status(HttpStatus.OK).body(productList);
//    }
//    查詢商品Id
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        if(product !=null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
/*  新增商品功能
    1. public ResponseEntity<Product> createProduct(Product product){
        不建議直接傳 Product類別 來新增 因為要決定前端傳來得值 並驗證
        另一種作法 會另外創一個類別來判斷
    2. @Valid 會加 常忘記
    3. 實作功能 dao > service > controller or
        controller > service > dao
 */
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId=productService.createProduct(productRequest);
        Product product=productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
//   修改商品功能
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
//        可以再修改 先檢查商品是否存在
        Product product = productService.getProductById(productId);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
//        修改商品數據
        productService.updateProduct(productId,productRequest);
        Product updateProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }
//    刪除商品功能
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
