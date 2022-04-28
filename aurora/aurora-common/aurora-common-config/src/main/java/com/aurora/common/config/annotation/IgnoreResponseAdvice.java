package com.aurora.common.config.annotation;

import com.aurora.common.core.enums.Enabled;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/1/30
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {

    Enabled value() default Enabled.TRUE;
}
