package com.aurora.auth.service;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/6 12:43
 * @Version 1.0
 */
public interface LoginService {

    /**
     * 获取token
     *
     * @param username
     * @param password
     * @return
     */
    String createToken(String username, String password);
}
