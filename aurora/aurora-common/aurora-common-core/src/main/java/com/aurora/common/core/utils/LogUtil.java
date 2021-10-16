package com.aurora.common.core.utils;

/**
 * describe: 处理并记录日志文件
 *
 * @Author Guo Huaijian
 * @Date 2021/10/15
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public class LogUtil {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
