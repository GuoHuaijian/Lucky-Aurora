package com.aurora.common.security.handler;

import com.aurora.common.core.enums.HttpStatusEnum;
import com.aurora.common.core.web.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * describe: 无权限处理类
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Component
@Slf4j
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        Result.responseJson(response, Result.error(HttpStatusEnum.FORBIDDEN.getCode(), accessDeniedException.getMessage()));
    }

}
