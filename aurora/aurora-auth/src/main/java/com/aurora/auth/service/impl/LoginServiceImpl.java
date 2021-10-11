package com.aurora.auth.service.impl;

import com.aurora.auth.service.LoginService;
import com.aurora.common.security.domain.SecurityUser;
import com.aurora.common.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/6 12:42
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

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
