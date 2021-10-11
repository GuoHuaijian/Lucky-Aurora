package com.aurora.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * describe: 访问日志记录
 *
 * @Author Guo Huaijian
 * @Date 2021/10/11
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_visit_log")
public class SysVisitLog implements Serializable {
    @TableId(value = "visit_id", type = IdType.AUTO)
    private Integer visitId;

    /**
     * 请求的模块
     */
    @TableField(value = "title")
    private String title;

    /**
     * 博客文章的id
     */
    @TableField(value = "blog_id")
    private Integer blogId;

    /**
     * ip地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 访问地址
     */
    @TableField(value = "location")
    private String location;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    /**
     * 请求地址
     */
    @TableField(value = "entry_url")
    private String entryUrl;

    /**
     * 请求地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 爬虫
     */
    @TableField(value = "spider")
    private String spider;

    /**
     * 访问错误的提示信息
     */
    @TableField(value = "error_msg")
    private String errorMsg;

    /**
     * 访问状态,0表示正常，1表示不正常
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(value = "visit_time")
    private Date visitTime;

    private static final long serialVersionUID = 1L;
}
