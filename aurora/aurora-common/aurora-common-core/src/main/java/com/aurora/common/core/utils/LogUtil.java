package com.aurora.common.core.utils;

/**
 * describe: 处理并记录日志文件
 *
 * @author Guo Huaijian
 * @date 2021/10/15
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public class LogUtil {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
