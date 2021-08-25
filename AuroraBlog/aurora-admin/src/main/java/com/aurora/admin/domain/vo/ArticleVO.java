package com.aurora.admin.domain.vo;

import com.aurora.admin.domain.Article;
import com.aurora.admin.domain.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleVO extends Article {

    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;
}
