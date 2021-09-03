package com.aurora.common.security.handler;

import com.aurora.common.core.web.domain.Result;
import com.aurora.common.security.domain.SecurityUser;
import com.aurora.common.security.utils.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * describe: 登录成功处理类
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String token = JwtTokenUtil.createAccessToken(securityUser);
        Map<String, String> tokenMap = new HashMap<>(1);
        tokenMap.put("token", token);
        Result.responseJson(response, Result.success());
    }
}
