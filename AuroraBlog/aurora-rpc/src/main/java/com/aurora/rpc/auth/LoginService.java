package com.aurora.rpc.auth;

/**
 * describe: 登录认证接口
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
public interface LoginService {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

}
