package com.garylee.springbootlabshoppingmall.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductCategory {
    FOOD,
    CAR,
    @JsonProperty("E-BOOK")
    E_BOOK,
}
