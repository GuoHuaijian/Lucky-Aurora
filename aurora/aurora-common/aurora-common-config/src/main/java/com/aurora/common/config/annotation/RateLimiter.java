package com.aurora.common.config.annotation;

import com.aurora.common.core.constant.Constants;
import com.aurora.common.core.enums.LimitType;

import java.lang.annotation.*;

/**
 * describe: 限流注解
 *
 * @Author Guo
 * @Date 2021/10/25 17:08
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    String key() default Constants.RATE_LIMIT_KEY;

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 100;

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;
}
