package com.aurora.admin.service;

import com.aurora.admin.domain.Article;
import com.aurora.admin.domain.vo.ArticleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
public interface ArticleService extends IService<Article> {


    /**
     * 获取文章列表
     *
     * @param title
     * @return
     */
    List<ArticleVO> getArticleList(String title);

}
