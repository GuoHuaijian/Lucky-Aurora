package com.aurora.common.security.handler;

import com.aurora.common.core.web.domain.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * describe: 退出成功处理类
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        SecurityContextHolder.clearContext();
        Result.responseJson(response, Result.OK());
    }
}
