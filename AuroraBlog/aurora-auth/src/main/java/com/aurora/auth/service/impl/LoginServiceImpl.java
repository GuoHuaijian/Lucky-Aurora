package com.aurora.auth.service.impl;

import com.aurora.rpc.auth.LoginService;
import com.aurora.common.security.domain.SecurityUserDetails;
import com.aurora.common.security.utils.JWTTokenUtil;
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
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
@DubboService
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserDetailsService userDetailsService;

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
        if (!new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
            log.debug("用户名或密码错误");
            throw new BadCredentialsException("用户名或密码错误");
        }
        String token = JWTTokenUtil.createAccessToken(userDetails);
        return token;
    }
}
