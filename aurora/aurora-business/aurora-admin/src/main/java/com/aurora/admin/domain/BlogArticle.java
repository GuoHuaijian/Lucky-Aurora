package com.aurora.admin.domain;

import com.aurora.common.core.web.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * describe: 文章
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blog_article")
public class BlogArticle extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    /**
     * 文章标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文章描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 文章作者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 文章内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * html的content
     */
    @TableField(value = "content_format")
    private String contentFormat;

    /**
     * 阅读量
     */
    @TableField(value = "read_num")
    private Integer readNum;

    /**
     * 评论量
     */
    @TableField(value = "comment_num")
    private Integer commentNum;

    /**
     * 点赞量
     */
    @TableField(value = "like_num")
    private Integer likeNum;

    /**
     * 文章展示类别,1:普通，2：大图片，3：无图片
     */
    @TableField(value = "cover_type")
    private Integer coverType;

    /**
     * 封面
     */
    @TableField(value = "cover_url")
    private String coverUrl;

    /**
     * 是否推荐文章
     */
    @TableField(value = "is_recommend")
    private Boolean isRecommend;

    /**
     * 分类类别存在多级分类，用逗号隔开
     */
    @TableField(value = "category_id")
    private String categoryId;

    /**
     * 发布状态
     */
    @TableField(value = "publish")
    private Byte publish;

    /**
     * 是否置顶
     */
    @TableField(value = "is_top")
    private Boolean isTop;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 分类
     */
    @TableField(exist = false)
    private BlogCategory category;

    /**
     * 标签列表
     */
    @TableField(exist = false)
    private List<BlogTag> tags;

    private static final long serialVersionUID = 1L;
}
