package com.hotel.minapp.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JWTUtil {

    private static String SECRET = "123456";
    private static final String HEADER_STRING = "token";
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  jwt
     * @param openId 小程序ID
     * @return 是否正确
     */
    public static boolean verify(String token, String openId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("openId", openId)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 获得token中的信息无需secret解密也能
     *
     * @return token中包含的用户名
     */
    public static String getOpenId(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            try {
                DecodedJWT jwt = JWT.decode(token);
                return jwt.getClaim("openId").asString();
            } catch (JWTDecodeException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 生成签名,5min后过期
     *
     * @param openId 用户名
     * @return 加密的token
     */
    public static String sign(String openId) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        String token = JWT.create()
                .withClaim("openId", openId)
                .withExpiresAt(date)
                .sign(algorithm);
        return token;
    }


    public static Integer getWxUserId(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("wxUserId").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从 request 的 header 中获取 JWT
     *
     * @param request 请求
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}