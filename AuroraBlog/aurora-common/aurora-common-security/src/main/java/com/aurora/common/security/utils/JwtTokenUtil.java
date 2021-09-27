//package com.aurora.common.security.utils;
//
//import cn.hutool.core.util.StrUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.aurora.common.core.utils.ServletUtil;
//import com.aurora.common.security.config.IgnoreUrlProperties;
//import com.aurora.common.security.domain.SecurityUser;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.util.Assert;
//
//import java.util.*;
//
///**
// * describe: JWT生产Token工具类
// *
// * @Author Guo Huaijian
// * @Date 2021/1/1
// * @E-mail guohuaijian9527@gmail.com
// * @Version 1.0
// */
//@Slf4j
//public class JwtTokenUtil {
//
//    private static final Long MILLIS_MINUTE_TEN = 30 * 60 * 1000L;
//
//    /**
//     * 签发者
//     */
//    private static final String ISSUER = "aurora";
//
//    /**
//     * 用户信息常量
//     */
//    private static final String USER_DETAILS = "userDetails";
//
//    /**
//     * 用户角色权限常量
//     */
//    private static final String AUTHORITIES = "authorities";
//
//    /**
//     * 创建Token
//     *
//     * @param securityUser 用户信息
//     * @return
//     */
//    public static String createAccessToken(SecurityUser securityUser) {
//        // 设置JWT
//        String token = Jwts.builder().setId(
//                // 用户Id
//                securityUser.getUserId().toString())
//                // 主题
//                .setSubject(securityUser.getUsername())
//                // 签发时间
//                .setIssuedAt(new Date())
//                // 签发者
//                .setIssuer(ISSUER)
//                // 过期时间
//                .setExpiration(new Date(System.currentTimeMillis() + IgnoreUrlProperties.expiration))
//                // 签名算法、密钥
//                .signWith(SignatureAlgorithm.HS512, IgnoreUrlProperties.secret)
//                // 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
//                .claim(USER_DETAILS, JSON.toJSONString(securityUser))
//                .claim(AUTHORITIES, JSON.toJSONString(securityUser.getAuthorities())).compact();
//        return IgnoreUrlProperties.tokenPrefix + token;
//    }
//
//    /**
//     * 解析Token
//     *
//     * @param token Token信息
//     * @return
//     */
//    public static SecurityUser parseAccessToken(String token) {
//        SecurityUser securityUser = null;
//        if (StrUtil.isNotEmpty(token)) {
//            Assert.isTrue(isTokenExpired(token), "token过期");
//            try {
//                securityUser = getSecurityUser(token);
//                Set<GrantedAuthority> authorities = getAuthorities(token);
//                securityUser.setAuthorities(authorities);
//            } catch (Exception e) {
//                log.error("解析Token异常：" + e);
//            }
//        }
//        return securityUser;
//    }
//
//    /**
//     * 刷新token
//     */
//    public static void refreshToken(String token) {
//        SecurityUser securityUser = getSecurityUser(token);
//        String newToken = createAccessToken(securityUser);
//        ServletUtil.getResponse().addHeader(IgnoreUrlProperties.tokenHeader, newToken);
//    }
//
//    /**
//     * 验证token是否过期
//     *
//     * @param token
//     * @return
//     */
//    public static boolean isTokenExpired(String token) {
//        Claims claims = getClaimsFromToken(token);
//        if (claims == null) {
//            return false;
//        }
//        Long expireTime = claims.getExpiration().getTime();
//        Long currentTime = System.currentTimeMillis();
//        // token后三十分钟之内都可以用该token获取新的token
//        if ((expireTime - currentTime) <= MILLIS_MINUTE_TEN) {
//            refreshToken(token);
//        }
//        return true;
//    }
//
//    /**
//     * 获取Claims
//     *
//     * @param token
//     * @return
//     */
//    public static Claims getClaimsFromToken(String token) {
//        Claims claims;
//        // 去除JWT前缀
//        token = token.substring(IgnoreUrlProperties.tokenPrefix.length());
//        try {
//            // 解析Token
//            claims = Jwts.parser().setSigningKey(IgnoreUrlProperties.secret).parseClaimsJws(token).getBody();
//        } catch (ExpiredJwtException e) {
//            // token过期
//            claims = null;
//        } catch (Exception e) {
//            throw new RuntimeException("解析Token异常");
//        }
//        return claims;
//    }
//
//    /**
//     * 从token中获取用户信息
//     *
//     * @param token
//     * @return
//     */
//    public static SecurityUser getSecurityUser(String token) {
//        Claims claims = getClaimsFromToken(token);
//        // 获取用户信息
//        SecurityUser securityUser = JSON.parseObject(claims.get(USER_DETAILS).toString(),
//                SecurityUser.class);
//        return securityUser;
//    }
//
//    /**
//     * 从token中获取角色权限
//     *
//     * @param token
//     * @return
//     */
//    public static Set<GrantedAuthority> getAuthorities(String token) {
//        Claims claims = getClaimsFromToken(token);
//        // 获取角色
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        String authority = claims.get(AUTHORITIES).toString();
//        if (StrUtil.isNotEmpty(authority)) {
//            List<Map<String, String>> authorityList = JSON.parseObject(authority,
//                    new TypeReference<List<Map<String, String>>>() {
//                    });
//            for (Map<String, String> auth : authorityList) {
//                Optional.ofNullable(auth).ifPresent(role
//                        -> authorities.add(new SimpleGrantedAuthority(role.get("authority"))));
//            }
//        }
//        return authorities;
//    }
//
//}
