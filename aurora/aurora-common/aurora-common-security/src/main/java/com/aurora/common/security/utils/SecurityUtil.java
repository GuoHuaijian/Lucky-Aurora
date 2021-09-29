package com.aurora.common.security.utils;

import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.security.domain.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 10:28
 * @Version 1.0
 */
public class SecurityUtil {

    /**
     * HeaderKey
     */
    public static final String  tokenHeader = "Authorization";

    /**
     * Token前缀
     */
    public static final String tokenPrefix = "Bearer ";

    /**
     * 获取用户
     *
     * @return
     */
    public static SecurityUser getSecurityUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SecurityUser){
            SecurityUser user = (SecurityUser) principal;
            return user;
        }
        throw new IllegalArgumentException("获取当前用户失败");
    }

    /**
     * 获取用户name
     */
    public static String getUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getSecurityUser().getUserId();
    }

    /**
     * 获取请求token
     */
    public static String getToken() {
        return getToken(ServletUtil.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return token;
    }

    /**
     * 替换token前缀
     */
    public static String replaceTokenPrefix(String token) {
        token = token.substring(tokenPrefix.length());
        return token;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
