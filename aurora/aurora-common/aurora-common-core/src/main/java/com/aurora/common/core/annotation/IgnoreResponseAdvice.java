package com.aurora.common.core.annotation;

import com.aurora.common.core.enums.Enabled;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/8/26 14:00
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {

    Enabled value() default Enabled.TRUE;
}
