package com.aurora.common.rocketmq.annotation;

import com.aurora.common.rocketmq.config.RocketMqAutoConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/24 15:53
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RocketMqAutoConfigure.class})
public @interface EnableAuroraMq {
}
