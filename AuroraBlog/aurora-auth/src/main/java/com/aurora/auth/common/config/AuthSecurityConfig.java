package com.aurora.auth.common.config;

import com.aurora.auth.service.impl.UserDetailsServiceImpl;
import com.aurora.common.security.config.SecurityConfig;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
public class AuthSecurityConfig extends SecurityConfig {

    /**
     * 用户登录验证
     */
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 用户登录验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
