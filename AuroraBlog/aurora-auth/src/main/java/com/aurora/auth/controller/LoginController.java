package com.aurora.auth.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.aurora.auth.service.LoginService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

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

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("user")
    public  Object login(String username, String password){
        String codeUrl = "http://localhost:8081/oauth/token";
        // 通过 HTTP 获取token
        Map<String, Object> params = Maps.newHashMap();
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", "password");
        params.put("client_id", "aurora_client");
        params.put("client_secret", "aurora_secret");
        try {
             // 解析响应结果封装并返回
            String jsonStr = HttpUtil.post(codeUrl, params);
            Map<String, Object> tokenMap = (Map) JSONObject.parse(jsonStr);
            return tokenMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
