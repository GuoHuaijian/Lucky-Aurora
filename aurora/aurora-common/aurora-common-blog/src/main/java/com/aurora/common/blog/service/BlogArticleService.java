package com.aurora.common.blog.service;

import com.aurora.common.blog.domain.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/16
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface BlogArticleService extends IService<BlogArticle> {

    /**
     * 分页查询博文列表
     *
     * @param article
     * @return
     */
    List<BlogArticle> list(BlogArticle article);

    /**
     * 保存博文文章
     *
     * @param article
     * @return
     */
    boolean saveArticle(BlogArticle article);

    /**
     * 批量删除
     *
     * @param articleIds
     * @return
     */
    boolean deleteBatch(List<Integer> articleIds);

    /**
     * 更新博文
     *
     * @param article
     * @return
     */
    boolean updateArticle(BlogArticle article);

    /**
     * 获取文章
     *
     * @param articleId
     * @return
     */
    BlogArticle getArticle(Integer articleId);


}
