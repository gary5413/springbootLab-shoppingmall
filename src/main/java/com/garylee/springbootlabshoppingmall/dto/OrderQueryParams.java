package com.garylee.springbootlabshoppingmall.dto;

import lombok.Data;

@Data
public class OrderQueryParams {
    private Integer userId;
    private Integer limit;
    private Integer offset;
}
