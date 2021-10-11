package com.aurora.common.security.utils;

import cn.hutool.core.convert.Convert;
import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.security.config.JwtConfig;
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
     * 获取用户
     */
    public static String getUsername() {
        String username = JwtTokenUtil.parseAccessToken(getToken()).getUsername();
        return username;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return Convert.toLong(JwtTokenUtil.parseAccessToken(getToken()).getUserId());
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
        String token = request.getHeader(JwtConfig.tokenHeader);
        return token;
    }

    /**
     * 替换token前缀
     */
    public static String replaceTokenPrefix(String token) {
        token = token.substring(JwtConfig.tokenPrefix.length());
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
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
