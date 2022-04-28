package com.aurora.common.core.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/8/24
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
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
