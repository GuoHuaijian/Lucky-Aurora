package com.aurora.system.controller;

import com.aurora.common.security.domain.SecurityUserDetails;
import com.aurora.common.security.utils.JwtTokenUtil;
import com.aurora.rpc.auth.LoginService;
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

    @DubboReference
    private LoginService loginService;

    @PostMapping("user")
    public String getToken() {
        String admin = loginService.login("admin", "123456");
        SecurityUserDetails sysUserDetails = JwtTokenUtil.parseAccessToken(admin);
        System.out.println(sysUserDetails);
        return admin;
    }
}
