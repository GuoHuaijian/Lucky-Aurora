package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogArticle;
import com.aurora.admin.domain.BlogArticleTag;
import com.aurora.admin.domain.BlogCategory;
import com.aurora.admin.domain.BlogTag;
import com.aurora.admin.mapper.BlogArticleMapper;
import com.aurora.admin.service.BlogArticleService;
import com.aurora.admin.service.BlogArticleTagService;
import com.aurora.admin.service.BlogCategoryService;
import com.aurora.admin.service.BlogTagService;
import com.aurora.common.core.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private BlogTagService tagService;

    @Autowired
    private BlogArticleTagService articleTagService;

    /**
     * 分页查询博文列表
     *
     * @param article
     * @return
     */
    @Override
    public List<BlogArticle> list(BlogArticle article) {
        QueryWrapper<BlogArticle> wrapper = new QueryWrapper<>(article);
        if (StringUtil.isNotNull(article.getBeginTime())) {
            wrapper.lambda().ge(BlogArticle::getCreateTime, article.getBeginTime());
        }
        if (StringUtil.isNotNull(article.getEndTime())) {
            wrapper.lambda().le(BlogArticle::getCreateTime, article.getEndTime());
        }
        List<BlogArticle> articles = list(wrapper);
        for (BlogArticle blog : articles) {
            blog = setCategoryAndTag(blog);
        }
        return articles;
    }

    /**
     * 保存博文文章
     *
     * @param article
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveArticle(BlogArticle article) {
        save(article);
        return saveArticleTag(article.getTags(), article.getArticleId());
    }

    /**
     * 批量删除
     *
     * @param articleIds
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteBatch(List<Integer> articleIds) {
        deleteArticleTag(articleIds);
        return removeByIds(articleIds);
    }

    /**
     * 更新博文
     *
     * @param article
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateArticle(BlogArticle article) {
        deleteArticleTag(Arrays.asList(article.getArticleId()));
        saveArticleTag(article.getTags(), article.getArticleId());
        return updateById(article);
    }

    /**
     * 获取文章
     *
     * @param articleId
     * @return
     */
    @Override
    public BlogArticle getArticle(Integer articleId) {
        BlogArticle article = getById(articleId);
        return setCategoryAndTag(article);
    }

    /**
     * 添加博文标签关联
     *
     * @param tags
     * @param articleId
     */
    public boolean saveArticleTag(List<BlogTag> tags, Integer articleId) {
        if (StringUtil.isNotEmpty(tags)) {
            List<BlogArticleTag> articleTags = Lists.newArrayList();
            tags.forEach(tag -> articleTags.add(new BlogArticleTag(tag.getTagId(), articleId)));
            return articleTagService.saveBatch(articleTags);
        }
        return true;
    }

    /**
     * 删除博文标签关联
     *
     * @param articleIds
     */
    public boolean deleteArticleTag(List<Integer> articleIds) {
        return articleTagService.remove(new LambdaQueryWrapper<BlogArticleTag>().in(BlogArticleTag::getArticleId,
                articleIds));
    }

    /**
     * 添加分类和标签
     *
     * @param article
     * @return
     */
    public BlogArticle setCategoryAndTag(BlogArticle article) {
        BlogCategory category = categoryService.getById(article.getCategoryId());
        article.setCategory(category);
        List<BlogArticleTag> articleTags = articleTagService.list(new LambdaQueryWrapper<BlogArticleTag>().eq(BlogArticleTag::getArticleId,
                article.getArticleId()));
        List<Integer> tagIds = Lists.newArrayList();
        articleTags.forEach(articleTag -> tagIds.add(articleTag.getTagId()));
        if (tagIds.size() > 0) {
            List<BlogTag> tags = tagService.list(new LambdaQueryWrapper<BlogTag>().in(BlogTag::getTagId, tagIds));
            article.setTags(tags);
        }
        return article;
    }
}
