package com.lishuai.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * JWT工具
 * @author lishuai
 */
public class JwtUtils {
    private static final String TOKEN = "token!Q@W3e4r";

    /**
     * 生成token
     * @param map
     * @return 返回 token
     * 传入payload
     */
    public static String createToken(Map<String,String> map){
        //创建jwt的builder
        JWTCreator.Builder builder = JWT.create();
        //传入map信息，构建payload
        map.forEach(builder::withClaim);
        //设置签名有效时间为一周
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        builder.withExpiresAt(instance.getTime());
        //创建token
        return builder.sign(Algorithm.HMAC256(TOKEN)).toString();
    }
    /**
     * 验证token,不合法就抛出异常
     * @param token
     * @return 验证不通过会抛出异常
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
    /**
     * 获取token中 payload
     * @param token
     * @return 返回 DecodedJWT
     */
    public static DecodedJWT analysisToken(String token){
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}
