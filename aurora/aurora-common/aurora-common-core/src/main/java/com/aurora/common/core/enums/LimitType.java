package com.aurora.common.core.enums;

/**
 * describe: 限流类型
 *
 * @Author Guo
 * @Date 2021/10/25 17:08
 * @Version 1.0
 */
public enum LimitType {

    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP
}
