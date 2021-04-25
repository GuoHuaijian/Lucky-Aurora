package com.aurora.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */

/**
 * 笔记
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dbblog.book_note")
public class BookNote implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 笔记标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 笔记描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 笔记作者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 笔记内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * html的context
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
     * 封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 所属书本
     */
    @TableField(value = "book_id")
    private Integer bookId;

    /**
     * 所属章节
     */
    @TableField(value = "chapter")
    private String chapter;

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
     * 是否推荐笔记
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
     * 封面类型
     */
    @TableField(value = "cover_type")
    private Integer coverType;

    /**
     * 是否置顶
     */
    @TableField(value = "top")
    private Boolean top;

    private static final long serialVersionUID = 1L;
}
