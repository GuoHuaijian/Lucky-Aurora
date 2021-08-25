package com.aurora.common.security.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aurora.common.security.config.JwtConfig;
import com.aurora.common.security.domain.SecurityUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import java.util.*;

/**
 * describe: JWT生产Token工具类
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Slf4j
public class JwtTokenUtil {

    /**
     * 创建Token
     *
     * @param userDetails 用户信息
     * @return
     */
    public static String createAccessToken(SecurityUserDetails userDetails) {
        // 设置JWT
        String token = Jwts.builder().setId(
                // 用户Id
                userDetails.getUserId().toString())
                // 主题
                .setSubject(userDetails.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("aurora")
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.expiration))
                // 签名算法、密钥
                .signWith(SignatureAlgorithm.HS512, JwtConfig.secret)
                // 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
                .claim("userDetails", JSON.toJSONString(userDetails))
                .claim("authorities", JSON.toJSONString(userDetails.getAuthorities())).compact();
        return JwtConfig.tokenPrefix + token;
    }

    /**
     * 解析Token
     *
     * @param token Token信息
     * @return
     */
    public static SecurityUserDetails parseAccessToken(String token) {
        SecurityUserDetails userDetails = null;
        if (StrUtil.isNotEmpty(token)) {
            Assert.isTrue(JwtTokenUtil.isTokenExpired(token), "token过期");
            try {
                Claims claims = getClaimsFromToken(token);
                // 获取用户信息
                userDetails = JSON.parseObject(claims.get("userDetails").toString(), SecurityUserDetails.class);
                // 获取角色
                Set<GrantedAuthority> authorities = new HashSet<>();
                String authority = claims.get("authorities").toString();
                if (StrUtil.isNotEmpty(authority)) {
                    List<Map<String, String>> authorityList = JSON.parseObject(authority,
                            new TypeReference<List<Map<String, String>>>() {
                            });
                    for (Map<String, String> auth : authorityList) {
                        Optional.ofNullable(auth).ifPresent(role
                                -> authorities.add(new SimpleGrantedAuthority(role.get("authority"))));
                    }
                }
                userDetails.setAuthorities(authorities);
            } catch (Exception e) {
                log.error("解析Token异常：" + e);
            }
        }
        return userDetails;
    }

    /**
     * 验证token是否过期
     *
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        return (claims.getExpiration().before(new Date()));
    }

    /**
     * 获取Claims
     *
     * @param token
     * @return
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        // 去除JWT前缀
        token = token.substring(JwtConfig.tokenPrefix.length());
        try {
            // 解析Token
            claims = Jwts.parser().setSigningKey(JwtConfig.secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
