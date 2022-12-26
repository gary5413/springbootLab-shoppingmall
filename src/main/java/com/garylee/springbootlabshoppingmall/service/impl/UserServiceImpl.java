package com.garylee.springbootlabshoppingmall.service.impl;

import com.garylee.springbootlabshoppingmall.dao.UserDao;
import com.garylee.springbootlabshoppingmall.dto.UserRegisterRequest;
import com.garylee.springbootlabshoppingmall.model.User;
import com.garylee.springbootlabshoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
