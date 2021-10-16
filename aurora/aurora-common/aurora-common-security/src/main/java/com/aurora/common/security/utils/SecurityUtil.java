package com.aurora.common.security.utils;

import com.aurora.common.core.constant.Constants;
import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.security.domain.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/7
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public class SecurityUtil {

    /**
     * HeaderKey
     */
    public static final String tokenHeader = "Authorization";

    /**
     * Token前缀
     */
    public static final String tokenPrefix = "Bearer ";

    /**
     * 获取用户
     *
     * @return
     */
    public static SecurityUser getSecurityUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SecurityUser) {
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
        String token = getRequestToken(ServletUtil.getRequest());
        if (StringUtil.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 根据request获取请求token
     */
    public static String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return token;
    }

    /**
     * 替换token前缀
     */
    public static String replaceTokenPrefix(String token) {
        token = token.substring(Constants.TOKEN_PREFIX.length());
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

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
