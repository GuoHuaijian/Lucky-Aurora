package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogArticle;
import com.aurora.admin.mapper.BlogArticleMapper;
import com.aurora.admin.service.BlogArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 13:20
 * @Version 1.0
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Override
    public List<BlogArticle> getArticleList(String title) {
        List<BlogArticle> list = this.list(new QueryWrapper<BlogArticle>()
                .lambda().eq(BlogArticle::getTitle, title));
        return list;
    }
}
