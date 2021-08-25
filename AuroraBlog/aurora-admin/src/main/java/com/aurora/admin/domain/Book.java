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
 * describe:图书表
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
@TableName(value = "dbblog.book")
public class Book implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 作者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 分类类别
     */
    @TableField(value = "category_id")
    private String categoryId;

    /**
     * 是否推荐
     */
    @TableField(value = "recommend")
    private Boolean recommend;

    /**
     * 出版社
     */
    @TableField(value = "publisher")
    private String publisher;

    /**
     * 出版日期
     */
    @TableField(value = "publish_date")
    private Date publishDate;

    /**
     * 页数
     */
    @TableField(value = "page_num")
    private Integer pageNum;

    /**
     * 评分
     */
    @TableField(value = "grade")
    private Double grade;

    /**
     * 简介
     */
    @TableField(value = "description")
    private String description;

    /**
     * 原书目录
     */
    @TableField(value = "catalogue")
    private String catalogue;

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
     * 是否发布
     */
    @TableField(value = "publish")
    private Boolean publish;

    /**
     * 读书状态
     */
    @TableField(value = "progress")
    private Integer progress;

    /**
     * 是否阅读
     */
    @TableField(value = "reading")
    private Boolean reading;

    private static final long serialVersionUID = 1L;
}
