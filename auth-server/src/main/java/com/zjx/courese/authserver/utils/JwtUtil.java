package com.zjx.courese.authserver.utils;


import com.zjx.courese.authserver.bean.ComUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class JwtUtil {

    //    private long expire;
//    private static final String secret = "admin";
    private String header;

    // 生成jwt
    public static String generateToken(ComUser user) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * 604800);
//        Map<String, Object> userMap = new HashMap<>();
//        userMap.put("user",user);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(user.getUsername())//主题
                .setIssuedAt(nowDate) //jwt的签发时间
                .setExpiration(expireDate) // 7天過期
//                .setPayload(String.valueOf(user))//设置载荷  payload和claims不能同时指定
                .claim("user",user)
                .signWith(SignatureAlgorithm.HS512, "admin")//指定加密算法
                .compact();
    }

    // 解析jwt
    public static Claims getClaimByToken(String jwt) {
        try {
            return (Claims) Jwts.parser()
                    .setSigningKey("admin")
//                    .parseClaimsJwt(jwt)
                    .parse(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // jwt是否过期
    public static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}

