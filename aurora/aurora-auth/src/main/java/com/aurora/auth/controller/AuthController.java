package com.aurora.auth.controller;

import com.aurora.auth.utils.AuthUtil;
import com.aurora.common.core.web.domain.Result;
import com.google.common.collect.Maps;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * describe: 第三方登录
 *
 * @Author Guo Huaijian
 * @Date 2021/10/29
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    /**
     * 生成授权链接
     *
     * @param source
     * @return
     */
    @GetMapping(value = "/render")
    public Result getAuthorize(@RequestParam(value = "source") String source) {
        AuthRequest authRequest = AuthUtil.getAuthRequest(source);
        // 生成状态码
        String state = AuthStateUtils.createState();
        //  生成授权链接
        String authorizeUrl = authRequest.authorize(state);
        HashMap<String, String> result = Maps.newHashMap();
        result.put("authorizeUrl", authorizeUrl);
        // 将状态码给客户端，授权成功后获取用户信息时将state传回服务端，保证请求完整性，防止CSRF风险
        result.put("state", state);
        return Result.success(result);
    }

    /**
     * 根据code获取信息
     *
     * @param callback
     * @param source
     * @return
     */
    @PostMapping(value = "/getInfo")
    public Result authorizeLogin(AuthCallback callback, String source) {
        AuthRequest authRequest = AuthUtil.getAuthRequest(source);
        System.out.println(callback);
        // 用户信息
        AuthResponse<?> result;
        result = authRequest.login(callback);
        if (result.getData() == null) {
            // 授权失败, 返回错误信息
            return Result.error(result.getMsg());
        }
        Object data = result.getData();
        return Result.success(data);
    }
}
