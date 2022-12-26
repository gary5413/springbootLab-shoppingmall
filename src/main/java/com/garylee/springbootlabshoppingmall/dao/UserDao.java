package com.garylee.springbootlabshoppingmall.dao;

import com.garylee.springbootlabshoppingmall.dto.UserRegisterRequest;
import com.garylee.springbootlabshoppingmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}
