package com.aurora.common.blog.domain;

import com.aurora.common.core.web.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * describe: 评论表
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blog_comment")
public class BlogComment extends BaseEntity implements Serializable {
    /**
     * 评论主键id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private String commentId;

    /**
     * 评论类型：0:留言 1:文章
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 被评论id，可以是单个文章id、项目、资源
     */
    @TableField(value = "owner_id")
    private String ownerId;

    /**
     * 评论id 第一级为0
     */
    @TableField(value = "parent_id")
    private String parentId;

    /**
     * 评论者名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 评论者头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 点赞的数量
     */
    @TableField(value = "like_num")
    private Integer likeNum;

    /**
     * 踩的数量
     */
    @TableField(value = "dislike_num")
    private Integer dislikeNum;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 回复的id
     */
    @TableField(value = "reply_id")
    private String replyId;

    /**
     * 回复评论者名字
     */
    @TableField(value = "reply_name")
    private String replyName;

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

    private static final long serialVersionUID = 1L;
}
