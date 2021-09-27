package com.aurora.auth.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/27
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("oauth")
public class UserController {

    /**
     * 获取当前用户
     *
     * @param principal
     * @return
     */
    @GetMapping("user")
    public Principal user(Principal principal){
        return principal;
    }
}
