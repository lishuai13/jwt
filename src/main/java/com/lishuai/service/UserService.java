package com.lishuai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.entity.User;
import com.lishuai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service
 * @author lishuai
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User login(String username,String password){
        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        queryWrapper.allEq(map);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}

