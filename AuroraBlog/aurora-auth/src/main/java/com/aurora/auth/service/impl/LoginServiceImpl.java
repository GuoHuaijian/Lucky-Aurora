package com.aurora.auth.service.impl;

import com.aurora.common.security.domain.SecurityUserDetails;
import com.aurora.common.security.utils.JwtTokenUtil;
import com.aurora.rpc.auth.LoginService;
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
@DubboService
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        // 此方法会调用userDetailsService AuthSecurityConfig中可以配置userDetailsService的实现
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityUserDetails userDetails = (SecurityUserDetails) authenticate.getPrincipal();
        String token = JwtTokenUtil.createAccessToken(userDetails);
        return token;
    }
}
