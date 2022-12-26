package com.garylee.springbootlabshoppingmall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterRequest {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
}
