package com.aurora.system.controller;

import com.aurora.common.security.domain.SecurityUser;
import com.aurora.common.security.utils.JwtTokenUtil;
import com.aurora.rpc.auth.AuthService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("login")
public class TestController {

    @DubboReference(version = "1.0.0", timeout = 5000, retries = 1)
    private AuthService authService;

    @PostMapping("user")
    public String getToken() {
        String admin = authService.createToken("admin", "123456");
        SecurityUser securityUser = JwtTokenUtil.parseAccessToken(admin);
        System.out.println(securityUser);
        return admin;
    }
}
