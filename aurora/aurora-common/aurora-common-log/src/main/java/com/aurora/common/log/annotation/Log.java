package com.aurora.common.log.annotation;

import com.aurora.common.log.enums.LogType;
import com.aurora.common.log.enums.OperatorType;

import java.lang.annotation.*;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/6 16:44
 * @Version 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String value() default "";

    /**
     * 日志类型
     */
    LogType LogType() default LogType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
