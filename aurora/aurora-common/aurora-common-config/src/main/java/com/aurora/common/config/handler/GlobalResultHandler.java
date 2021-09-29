package com.aurora.common.config.handler;

import com.alibaba.fastjson.JSON;
import com.aurora.common.core.annotation.IgnoreResponseAdvice;
import com.aurora.common.core.enums.Enabled;
import com.aurora.common.core.web.domain.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * describe: 统一返回数据格式
 *
 * @Author Guo
 * @Date 2021/8/26 14:16
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalResultHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 通过方法参数得到类名，然后得到类上的annotation，
        // 如果被IgnoreResponseAdvice标识就不拦截
        if (null == methodParameter.getDeclaringClass().getAnnotation(IgnoreResponseAdvice.class) && null == methodParameter.getMethod().getAnnotation(IgnoreResponseAdvice.class)) {
            return true;
        }
        if (null != methodParameter.getDeclaringClass().getAnnotation(IgnoreResponseAdvice.class) && Enabled.TRUE.equals(methodParameter.getDeclaringClass().getAnnotation(IgnoreResponseAdvice.class).value())) {
            return false;
        }
        // 方法上被标注，也不拦截
        if (Enabled.TRUE.equals(methodParameter.getMethod().getAnnotation(IgnoreResponseAdvice.class).value())) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Result result = Result.success();
        // 为空不做处理
        if (null == o) {
            // 返回的类型为Result也不处理
        } else if (o instanceof Result) {
            // 处理controller返回为字符串时, 转换报异常的bug（默认使用的jackson转换器会报类型转换的错）
            //（如果使用FastJsonHttpMessageConverter，则不需要加下面if判断）
            result = (Result<Object>) o;
        } else if (o instanceof String) {
            result.put(Result.DATA_TAG, o);
            return JSON.toJSONString(result);
        }
        // 处理返回类型非Result
        else {
            result.put(Result.DATA_TAG, o);
        }
        return result;
    }
}
