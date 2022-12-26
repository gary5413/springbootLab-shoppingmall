package com.garylee.springbootlabshoppingmall.dao.impl;

import com.garylee.springbootlabshoppingmall.dao.UserDao;
import com.garylee.springbootlabshoppingmall.dto.UserRegisterRequest;
import com.garylee.springbootlabshoppingmall.model.User;
import com.garylee.springbootlabshoppingmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql="INSERT INTO user(email, password, created_date, last_modified_date) " +
                "VALUES (:email, :password, :createdDate, :lastModifiedDate)";
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",userRegisterRequest.getEmail());
        map.put("password",userRegisterRequest.getPassword());
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int userId=keyHolder.getKey().intValue();
        return userId;
    }

    @Override
    public User getUserById(Integer userId) {
        String sql="SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM user WHERE user_id = :userId";
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        List<User> userList=namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if(userList.size()>0){
            return userList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql="SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM user WHERE email = :email";
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",email);
        List<User> userList=namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if(userList.size()>0){
            return userList.get(0);
        }else{
            return null;
        }
    }
}
