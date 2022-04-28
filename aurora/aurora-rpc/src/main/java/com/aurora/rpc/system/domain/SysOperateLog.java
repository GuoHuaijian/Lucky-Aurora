package com.aurora.rpc.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * describe: 操作日志记录
 *
 * @author Guo Huaijian
 * @date 2021/9/7
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_operate_log")
public class SysOperateLog implements Serializable {

    /**
     * 日志主键
     */
    @TableId(value = "operate_id", type = IdType.AUTO)
    private Long operateId;

    /**
     * 模块标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField(value = "log_type")
    private Integer logType;

    /**
     * 方法名称
     */
    @TableField(value = "method")
    private String method;

    /**
     * 请求方式
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField(value = "operator_type")
    private Integer operatorType;

    /**
     * 操作人员
     */
    @TableField(value = "operator")
    private String operator;

    /**
     * 请求URL
     */
    @TableField(value = "operate_url")
    private String operateUrl;

    /**
     * 主机地址
     */
    @TableField(value = "operate_ip")
    private String operateIp;

    /**
     * 操作地点
     */
    @TableField(value = "operate_location")
    private String operateLocation;

    /**
     * 请求参数
     */
    @TableField(value = "operate_param")
    private String operateParam;

    /**
     * 返回参数
     */
    @TableField(value = "json_result")
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 错误消息
     */
    @TableField(value = "error_msg")
    private String errorMsg;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "operate_time", fill = FieldFill.INSERT)
    private Date operateTime;

    private static final long serialVersionUID = 1L;
}
