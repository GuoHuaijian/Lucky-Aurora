package com.aurora.rpc.system.domain;

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

public class SysVisitLog implements Serializable {

    private Integer visitId;

    /**
     * 请求的模块
     */
    private String title;

    /**
     * 博客文章的id
     */
    private Integer blogId;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 访问地址
     */
    private String location;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 请求地址
     */
    private String entryUrl;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 爬虫
     */
    private String spider;

    /**
     * 访问错误的提示信息
     */
    private String errorMsg;

    /**
     * 访问状态,0表示正常，1表示不正常
     */
    private Integer status;

    private Date visitTime;

    private static final long serialVersionUID = 1L;
}
