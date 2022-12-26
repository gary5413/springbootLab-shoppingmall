package com.garylee.springbootlabshoppingmall.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
