package com.aurora.common.log.enums;

/**
 * describe: 操作人类别
 *
 * @Author Guo Huaijian
 * @Date 2021/9/6
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public enum OperatorType {
    /**
     * 其它
     */
    OTHER("其它", 0),

    /**
     * 后台用户
     */
    MANAGE("后台用户", 1),

    /**
     * 手机端用户
     */
    MOBILE("手机端用户", 2);

    OperatorType(String msg, Integer code) {
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
