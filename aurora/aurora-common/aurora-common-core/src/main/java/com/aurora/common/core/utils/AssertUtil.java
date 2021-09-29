package com.aurora.common.core.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/8/24
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Slf4j
public class AssertUtil extends Assert {

    public static void notNull(@Nullable Object object, RuntimeException e) {
        if (object == null) {
            log.debug(e.getMessage());
            throw e;
        }
    }

    public static void isTrue(boolean expression, RuntimeException e) {
        if (!expression) {
            log.debug(e.getMessage());
            throw e;
        }
    }

    public static void notTrue(boolean expression, RuntimeException e) {
        if (expression) {
            log.debug(e.getMessage());
            throw e;
        }
    }

}
