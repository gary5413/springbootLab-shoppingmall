package com.garylee.springbootlabshoppingmall.controller;

import com.garylee.springbootlabshoppingmall.dto.UserRegisterRequest;
import com.garylee.springbootlabshoppingmall.model.User;
import com.garylee.springbootlabshoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Validated UserRegisterRequest userRegisterRequest){
        Integer userId=userService.register(userRegisterRequest);
        User user=userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
