package com.aurora.common.log.enums;

/**
 * describe: 日志类型
 *
 * @Author Guo Huaijian
 * @Date 2021/9/6
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public enum LogType {

    /**
     * 其它
     */
    OTHER("其它", 0),

    /**
     * 新增
     */
    INSERT("新增", 1),

    /**
     * 修改
     */
    UPDATE("修改", 2),

    /**
     * 删除
     */
    DELETE("删除", 3),

    /**
     * 授权
     */
    GRANT("授权", 4),

    /**
     * 导出
     */
    EXPORT("导出", 5),

    /**
     * 导入
     */
    IMPORT("导入", 6),

    /**
     * 强退
     */
    FORCE("强退", 7),

    /**
     * 生成代码
     */
    GENCODE("生成代码", 8),

    /**
     * 清空数据
     */
    CLEAN("清空数据", 9);

    LogType(String msg, Integer code) {
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
