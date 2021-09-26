package com.aurora.system.controller;

import com.aurora.rpc.auth.RemoteAuthService;
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

    @DubboReference(version = "1.0.0", timeout = 50000, retries = 1)
    private RemoteAuthService remoteAuthService;

    @PostMapping("user")
    public String getToken() {
        String admin = remoteAuthService.createToken("admin", "123456");
//        SecurityUser securityUser = JwtTokenUtil.parseAccessToken(admin);
//        System.out.println(securityUser);
        return admin;
    }
}
