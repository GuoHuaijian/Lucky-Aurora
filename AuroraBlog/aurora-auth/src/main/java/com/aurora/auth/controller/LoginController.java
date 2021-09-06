package com.aurora.auth.controller;

import com.aurora.auth.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * describe: 登陆
 *
 * @Author Guo
 * @Date 2021/9/6 12:40
 * @Version 1.0
 */
@RequestMapping("login")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("user")
    public String login(String username, String password) {
        System.out.println(username);
        String token = loginService.createToken(username, password);
        return token;
    }

}
