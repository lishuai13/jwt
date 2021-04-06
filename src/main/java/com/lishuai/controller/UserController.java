package com.lishuai.controller;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.lishuai.entity.User;
import com.lishuai.service.UserService;
import com.lishuai.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * User 控制器
 * @author lishuai
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public  Map<String,Object> login(String username,String password){
        Map<String, Object> result = new HashMap<>();
        User user = userService.login(username, password);
        if (user!=null){
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(user.getId()));
            map.put("username", user.getUsername());
            String token = JwtUtils.createToken(map);
            result.put("state",true);
            result.put("msg","登录成功!!!");
            result.put("token",token);
        }else {
            result.put("state",false);
            result.put("msg","登录失败!!!");
        }
        return result;
    }

    @PostMapping("/verify")
    public Map<String, Object> verify(String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            JwtUtils.verify(token);
            map.put("msg", "验证通过!");
            map.put("state", true);
        } catch (TokenExpiredException e) {
            map.put("state", false);
            map.put("msg", "Token已经过期!!!");
        } catch (SignatureVerificationException e){
            map.put("state", false);
            map.put("msg", "签名错误!!!");
        } catch (AlgorithmMismatchException e){
            map.put("state", false);
            map.put("msg", "加密算法不匹配!!!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "无效token!");
        }
        return map;
    }
}
