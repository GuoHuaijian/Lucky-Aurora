package com.aurora.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * describe: 文章
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dbblog.article")
public class Article implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
    @TableField(value = "cover")
    private String cover;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否推荐文章
     */
    @TableField(value = "recommend")
    private Boolean recommend;

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
    @TableField(value = "top")
    private Boolean top;

    private static final long serialVersionUID = 1L;
}
