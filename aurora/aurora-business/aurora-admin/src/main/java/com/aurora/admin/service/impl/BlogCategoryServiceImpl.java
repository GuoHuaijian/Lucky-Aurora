package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogArticle;
import com.aurora.admin.domain.BlogCategory;
import com.aurora.admin.mapper.BlogCategoryMapper;
import com.aurora.admin.service.BlogArticleService;
import com.aurora.admin.service.BlogCategoryService;
import com.aurora.common.core.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Autowired
    private BlogArticleService articleService;

    /**
     * 查询分类
     *
     * @param categoryId 分类主键
     * @return 分类
     */
    @Override
    public BlogCategory selectBlogCategoryByCategoryId(Long categoryId) {
        return getById(categoryId);
    }

    /**
     * 查询分类列表
     *
     * @param category 分类
     * @return 分类
     */
    @Override
    public List<BlogCategory> selectBlogCategoryList(BlogCategory category) {
        return list(new QueryWrapper<>(category));
    }

    /**
     * 新增分类
     *
     * @param category 分类
     * @return 结果
     */
    @Override
    public boolean insertBlogCategory(BlogCategory category) {
        return save(category);
    }

    /**
     * 修改分类
     *
     * @param category 分类
     * @return 结果
     */
    @Override
    public boolean updateBlogCategory(BlogCategory category) {
        return updateById(category);
    }

    /**
     * 批量删除分类
     *
     * @param categoryIds 需要删除的分类主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogCategoryByCategoryIds(Long[] categoryIds) {
        int count = articleService.count(new LambdaQueryWrapper<BlogArticle>().in(BlogArticle::getCategoryId,
                categoryIds));
        if (count >= 1) {
            throw new ServiceException("删除分类失败,该分类下有关联博文");
        }
        return removeByIds(Arrays.asList(categoryIds));
    }

    /**
     * 删除分类信息
     *
     * @param categoryId 分类主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogCategoryByCategoryId(Long categoryId) {
        int count = articleService.count(new LambdaQueryWrapper<BlogArticle>().eq(BlogArticle::getCategoryId,
                categoryId));
        if (count >= 1) {
            throw new ServiceException("删除分类失败,该分类下有关联博文");
        }
        return removeById(categoryId);
    }
}
