package com.aurora.auth.service.impl;

import com.aurora.common.core.utils.AssertUtil;
import com.aurora.common.security.domain.SecurityUserDetails;
import com.aurora.common.security.utils.JwtTokenUtil;
import com.aurora.rpc.auth.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        SecurityUserDetails userDetails = (SecurityUserDetails) userDetailsService.loadUserByUsername(username);
        AssertUtil.isTrue(new BCryptPasswordEncoder().matches(password, userDetails.getPassword()), new BadCredentialsException("用户名或密码错误"));
        String token = JwtTokenUtil.createAccessToken(userDetails);
        return token;
    }
}
