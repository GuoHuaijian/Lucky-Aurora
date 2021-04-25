package com.aurora.common.security.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aurora.common.security.config.JWTConfig;
import com.aurora.common.security.domain.SecurityUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * describe: JWT生产Token工具类
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
@Slf4j
public class JWTTokenUtil {

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
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration))
                // 签名算法、密钥
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
                // 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
                .claim("userDetails", JSON.toJSONString(userDetails))
                .claim("authorities", JSON.toJSONString(userDetails.getAuthorities())).compact();
        return JWTConfig.tokenPrefix + token;
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
            try {
                // 去除JWT前缀
                token = token.substring(JWTConfig.tokenPrefix.length());
                // 解析Token
                Claims claims = Jwts.parser().setSigningKey(JWTConfig.secret).parseClaimsJws(token).getBody();
                // 获取用户信息
                userDetails = JSON.parseObject(claims.get("userDetails").toString(), SecurityUserDetails.class);
                // 获取角色
                Set<GrantedAuthority> authorities = new HashSet<>();
                String authority = claims.get("authorities").toString();
                if (StrUtil.isNotEmpty(authority)) {
                    List<Map<String, String>> authorityList = JSON.parseObject(authority,
                            new TypeReference<List<Map<String, String>>>() {
                            });
                    for (Map<String, String> role : authorityList) {
                        if (!role.isEmpty()) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                userDetails.setAuthorities(authorities);
            } catch (Exception e) {
                log.error("解析Token异常：" + e);
            }
        }
        return userDetails;
    }

}
