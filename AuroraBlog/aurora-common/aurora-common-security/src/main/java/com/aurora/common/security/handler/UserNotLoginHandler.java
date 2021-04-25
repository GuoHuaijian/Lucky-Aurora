package com.aurora.common.security.handler;

import com.aurora.common.core.enums.HttpStatusEnum;
import com.aurora.common.core.web.domain.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * describe: 未登录处理类
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        Result.responseJson(response, Result.error(HttpStatusEnum.UNAUTHORIZED));
    }
}
