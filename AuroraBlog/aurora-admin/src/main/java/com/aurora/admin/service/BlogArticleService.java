package com.aurora.admin.service;

import com.aurora.admin.domain.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 13:20
 * @Version 1.0
 */
public interface BlogArticleService extends IService<BlogArticle> {

    /**
     * 获取文章列表
     *
     * @param title
     * @return
     */
    List<BlogArticle> getArticleList(String title);

}
