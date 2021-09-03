package com.aurora.auth.service.impl;

import com.aurora.common.security.domain.SecurityUser;
import com.aurora.common.security.utils.JwtTokenUtil;
import com.aurora.rpc.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.annotation.Resource;


/**
 * describe: 登录方法实现
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@DubboService(version = "1.0.0", interfaceClass = AuthService.class)
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 获取token
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String createToken(String username, String password) {
        // 此方法会调用userDetailsService AuthSecurityConfig中可以配置userDetailsService的实现
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();
        String token = JwtTokenUtil.createAccessToken(securityUser);
        return token;
    }
}
