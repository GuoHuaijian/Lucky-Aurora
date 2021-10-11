package com.aurora.common.log.annotation;

import java.lang.annotation.*;

/**
 * describe: 访问日志记录注解
 *
 * @Author Guo Huaijian
 * @Date 2021/10/11
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VLog {

    /**
     * 请求的模块
     */
    String title() default "";

    /**
     * 文章id
     */
    String blogId() default "";
}
