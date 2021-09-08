package com.aurora.search.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/8 14:06
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "aurora_blog", indexStoreType = "article")
public class Article implements Serializable {
    /**
     * 主键
     */
    private Integer articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章描述
     */
    private String description;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章内容
     */
    private String content;

    /**
     * html的content
     */
    private String contentFormat;

    /**
     * 阅读量
     */
    private Integer readNum;

    /**
     * 评论量
     */
    private Integer commentNum;

    /**
     * 点赞量
     */
    private Integer likeNum;

    /**
     * 文章展示类别,1:普通，2：大图片，3：无图片
     */
    private Integer coverType;

    /**
     * 封面
     */
    private String cover;

    /**
     * 是否推荐文章
     */
    private Boolean isRecommend;

    /**
     * 分类类别存在多级分类，用逗号隔开
     */
    private String categoryId;

    /**
     * 发布状态
     */
    private Byte publish;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
