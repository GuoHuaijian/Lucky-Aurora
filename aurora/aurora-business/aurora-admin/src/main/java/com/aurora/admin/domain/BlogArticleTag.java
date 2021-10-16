package com.aurora.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * describe: 标签文章多对多维护表
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blog_article_tag")
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

    public BlogArticleTag(Integer tagId, Integer articleId) {
        this.tagId = tagId;
        this.articleId = articleId;
    }

    private static final long serialVersionUID = 1L;
}
