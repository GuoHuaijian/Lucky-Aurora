package com.aurora.admin.domain;

import com.aurora.common.core.web.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * describe: 友链
 *
 * @Author Guo Huaijian
 * @Date 2021/10/24
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blog_friend")
public class BlogFriend extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "friend_id", type = IdType.AUTO)
    private Long friendId;

    /**
     * 友链名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 图标url
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 简介
     */
    @TableField(value = "description")
    private String description;

    /**
     * 网站地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 站长邮箱
     */
    @TableField(value = "mail")
    private String mail;

    /**
     * 状态 0待审 1通过 2拒绝
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}

