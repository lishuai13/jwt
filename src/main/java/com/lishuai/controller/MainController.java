package com.lishuai.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lishuai.utils.JwtUtils;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 其他请求
 * @author lishuai
 */
@RestController
@RequestMapping("/main")
public class MainController {

    @PostMapping("/test")
    public Map<String,Object> test(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //处理逻辑
        String token = request.getHeader("token");
        DecodedJWT decodedJWT = JwtUtils.analysisToken(token);
        map.put("state",true);
        map.put("用户id:",decodedJWT.getClaim("id").asString());
        map.put("用户名:",decodedJWT.getClaim("username").asString());
        map.put("过期时间:",decodedJWT.getExpiresAt());
        return map;
    }
}
