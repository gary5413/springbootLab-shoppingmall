package com.garylee.springbootlabshoppingmall.service.impl;

import com.garylee.springbootlabshoppingmall.dao.UserDao;
import com.garylee.springbootlabshoppingmall.dto.UserLoginRequest;
import com.garylee.springbootlabshoppingmall.dto.UserRegisterRequest;
import com.garylee.springbootlabshoppingmall.model.User;
import com.garylee.springbootlabshoppingmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
//    加密版
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
//        檢查註冊Email
        User user=userDao.getUserByEmail(userRegisterRequest.getEmail());
        if(user !=null){
            log.warn("E-mail:{} 已被註冊",userRegisterRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
//        使用MD5生成
        String hashedPassword= DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);
//        創建帳號
        return userDao.createUser(userRegisterRequest);
    }

//    @Override
//    public Integer register(UserRegisterRequest userRegisterRequest) {
////        檢查註冊Email
//        User user=userDao.getUserByEmail(userRegisterRequest.getEmail());
//        if(user !=null){
//            log.warn("E-mail:{} 已被註冊",userRegisterRequest.getEmail());
//            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
////        創建帳號
//        return userDao.createUser(userRegisterRequest);
//    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
//    加密版
    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user=userDao.getUserByEmail(userLoginRequest.getEmail());
//        檢查User是否存在
        if(user ==null){
            log.warn("該Email:{}尚未被註冊",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
//        使用MD5生成密碼的雜湊值
        String hashedPassword=DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

//        比較密碼
        if(user.getPassword().equals(hashedPassword)){
            return user;
        }else{
            log.warn("email:{}的密碼不正確",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
//    @Override
//    public User login(UserLoginRequest userLoginRequest) {
//        User user=userDao.getUserByEmail(userLoginRequest.getEmail());
//        if(user ==null){
//            log.warn("該Email:{}尚未被註冊",userLoginRequest.getEmail());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//        if(user.getPassword().equals(userLoginRequest.getPassword())){
//            return user;
//        }else{
//            log.warn("email:{}的密碼不正確",userLoginRequest.getEmail());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }
}
