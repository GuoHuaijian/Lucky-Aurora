package com.aurora.system.controller;

import com.aurora.common.core.constant.Constants;
import com.aurora.common.core.manager.AsyncManager;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.common.factory.LogAsyncFactory;
import com.aurora.system.domain.LoginBody;
import com.aurora.system.domain.router.Router;
import com.aurora.system.service.LoginService;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/7
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RequestMapping("system")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 登录
     *
     * @param loginBody
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginBody loginBody) {
        String token = loginService.login(loginBody);
        Result result = Result.success();
        result.put(Constants.TOKEN, token);
        return result;
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

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("logout")
    public Result logout() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(new RedisTokenStore(redisConnectionFactory));
        String accessToken = SecurityUtil.getToken();
        boolean revokeToken = tokenServices.revokeToken(accessToken);
        if (revokeToken) {
            AsyncManager.me().execute(LogAsyncFactory.recordLoginLog(SecurityUtil.getUsername(), Constants.LOGOUT, "退出成功"));
            SecurityContextHolder.clearContext();
        }
        return Result.success("退出成功");
    }
}
