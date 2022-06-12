package com.aurora.common.blog.domain;

import com.aurora.common.core.web.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * describe: 评论表
 *
 * @author Guo Huaijian
 * @date 2021/10/16
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
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
    private Long commentId;

    /**
     * 评论类型：0:留言 1:文章
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 被评论id，可以是单个文章id、项目、资源
     */
    @TableField(value = "owner_id")
    private Long ownerId;

    /**
     * 评论id 第一级为0
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 评论者id
     */
    @TableField(value = "observer_id")
    private Long observerId;

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
    private Long replyId;

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
     * 评论者名字
     */
    @TableField(exist = false)
    private String name;

    /**
     * 评论者头像
     */
    @TableField(exist = false)
    private String avatar;

    /**
     * 回复评论者名字
     */
    @TableField(exist = false)
    private String replyName;

    /**
     * 评论的文章
     */
    @TableField(exist = false)
    private String articleTitle;

    /**
     * 回复
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<BlogComment> reply;

    private static final long serialVersionUID = 1L;
}
