package com.garylee.springbootlabshoppingmall.service;

import com.garylee.springbootlabshoppingmall.dto.UserRegisterRequest;
import com.garylee.springbootlabshoppingmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}
