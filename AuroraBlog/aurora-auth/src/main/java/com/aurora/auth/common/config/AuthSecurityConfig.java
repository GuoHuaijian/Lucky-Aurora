package com.aurora.auth.common.config;

import com.aurora.auth.service.impl.SysUserDetailsService;
import com.aurora.common.security.config.SysSecurityConfig;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
public class AuthSecurityConfig extends SysSecurityConfig {

    /**
     * 用户登录验证
     */
    @Resource
    private SysUserDetailsService userDetailsService;

    /**
     * 用户登录验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
