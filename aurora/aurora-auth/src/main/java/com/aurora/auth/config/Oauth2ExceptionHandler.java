package com.aurora.auth.config;

import com.aurora.common.core.web.domain.Result;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * describe: 全局处理Oauth2抛出的异常
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestControllerAdvice
public class Oauth2ExceptionHandler {

    @ExceptionHandler(value = OAuth2Exception.class)
    public Result handleOauth2(OAuth2Exception e) {
        return Result.error(e.getMessage());
    }
}