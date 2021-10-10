package com.aurora.system.controller;

import com.aurora.common.core.web.domain.Result;
import com.aurora.system.domain.router.Router;
import com.aurora.system.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/7
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RequestMapping("system")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public Result login(String username, String password) {
        Object result = loginService.login(username, password);
        return Result.success(result);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public Result getInfo() {
        Map<String, Object> info = loginService.getInfo();
        return Result.success(info);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public Result getRouters() {
        List<Router> routers = loginService.getRouters();
        return Result.success(routers);
    }
}
