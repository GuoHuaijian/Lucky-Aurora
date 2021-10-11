package com.aurora.common.log.enums;

/**
 * describe: 日志状态
 *
 * @Author Guo
 * @Date 2021/9/6 16:46
 * @Version 1.0
 */
public enum LogStatus {

    /**
     * 成功
     */
    SUCCESS("成功", 0),

    /**
     * 失败
     */
    ERROR("失败", 1);

    LogStatus(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    private String msg;

    private Integer code;

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

}
