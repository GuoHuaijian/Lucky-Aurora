package com.aurora.common.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * describe: JWT配置基础类
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@SuppressWarnings("static-access")
public class JwtConfig {

    /**
     * 密匙Key
     */
    public static String secret = "JWTSecret,C3Stones";

    /**
     * HeaderKey
     */
    public static String tokenHeader = "Authorization";

    /**
     * Token前缀
     */
    public static String tokenPrefix = "Bearer ";

    /**
     * 过期时间
     */
    public static Integer expiration = 86400;

    /**
     * 配置白名单
     */
    public static String antMatchers = "/login/**,/register/**,/static/**";

    /**
     * 将过期时间单位换算成毫秒
     *
     * @param expiration 过期时间，单位秒
     */
    public void setExpiration(Integer expiration) {
        JwtConfig.expiration = expiration * 1000;
    }

    public void setSecret(String secret) {
        JwtConfig.secret = secret;
    }

    public void setTokenHeader(String tokenHeader) {
        JwtConfig.tokenHeader = tokenHeader;
    }

    public void setTokenPrefix(String tokenPrefix) {
        JwtConfig.tokenPrefix = tokenPrefix + " ";
    }

    public void setAntMatchers(String antMatchers) {
        JwtConfig.antMatchers = antMatchers;
    }

}
