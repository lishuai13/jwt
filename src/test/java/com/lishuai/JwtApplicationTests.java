package com.lishuai;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lishuai.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
class JwtApplicationTests {

    //生成token
    @Test
    void createToken() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 90);
        //生成令牌
        String token = JWT.create()
                //设置payload,存放用户信息
                .withClaim("userId", 1)
                .withClaim("userName", "李帅")
                //设置签名过期时间
                .withExpiresAt(instance.getTime())
                //设置签名 保密 复杂
                .sign(Algorithm.HMAC256("token!Q2W#E$RW"));
        //输出令牌
        System.out.println(token);
    }

    //验证、解析token
    @Test
    void verifyToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IuadjuW4hSIsImV4cCI6MTYxNzYzMDQ2OSwidXNlcklkIjoxfQ.t3FaeCJqdvqaAO9wIh-G1kjCc305dCTlHcKlIyfriI0";
        //使用签名创建解析器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("token!Q2W#E$RW")).build();
        //解析
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        System.out.println("用户id: " + decodedJWT.getClaim("userId").asInt());
        System.out.println("用户名: " + decodedJWT.getClaim("userName").asString());
        System.out.println("过期时间: "+decodedJWT.getExpiresAt());

    }


    @Autowired
    UserMapper userMapper;

    @Test
    void testConnect(){
        userMapper.selectList(null).forEach(System.out::println);
    }
}
