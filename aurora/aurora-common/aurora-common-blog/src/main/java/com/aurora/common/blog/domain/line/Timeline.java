package com.aurora.common.blog.domain.line;

import lombok.Data;

import java.util.Date;

/**
 * describe: 时间线信息
 *
 * @Author Guo Huaijian
 * @Date 2022/1/19
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
public class Timeline {

    /**
     * 博文id
     */
    private Integer articleId;

    /**
     * 博文标题
     */
    private String title;

    /**
     * 博文描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;
}
