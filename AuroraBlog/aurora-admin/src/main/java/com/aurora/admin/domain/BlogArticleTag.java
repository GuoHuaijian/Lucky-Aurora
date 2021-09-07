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

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 13:20
 * @Version 1.0
 */

/**
 * 标签文章多对多维护表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "aurora_blog.blog_article_tag")
public class BlogArticleTag implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签Id
     */
    @TableField(value = "tag_id")
    private Integer tagId;

    /**
     * 文章Id
     */
    @TableField(value = "article_id")
    private Integer articleId;

    private static final long serialVersionUID = 1L;
}
