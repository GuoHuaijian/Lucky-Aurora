package com.aurora.common.core.enums;

/**
 * describe: 限流类型
 *
 * @author Guo Huaijian
 * @date 2021/10/25
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
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
