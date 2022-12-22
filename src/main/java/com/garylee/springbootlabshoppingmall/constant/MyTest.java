package com.garylee.springbootlabshoppingmall.constant;

public class MyTest {
    public static void main(String[] args) {
        ProductCategory category=ProductCategory.FOOD;
//      轉換String類型
        String test= category.name();
        System.out.println(test); // FOOD
        String test2="CAR";
//      查詢是否有符合test2=CAR的字串 返回
        ProductCategory category2=ProductCategory.valueOf(test2);
    }
}
