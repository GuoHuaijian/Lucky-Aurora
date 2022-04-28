package com.aurora.common.security.handler;

import com.aurora.common.core.enums.HttpStatusEnum;
import com.aurora.common.core.web.domain.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * describe: 认证失败（token无效过期等）
 *
 * @author Guo Huaijian
 * @date 2021/1/1
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        Result.responseJson(response, Result.error(HttpStatusEnum.UNAUTHORIZED));
    }
}
