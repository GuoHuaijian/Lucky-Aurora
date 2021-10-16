package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogArticle;
import com.aurora.admin.domain.BlogCategory;
import com.aurora.admin.mapper.BlogCategoryMapper;
import com.aurora.admin.service.BlogArticleService;
import com.aurora.admin.service.BlogCategoryService;
import com.aurora.common.core.exception.ServiceException;
import com.aurora.common.core.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 条件查询
     *
     * @param category
     * @return
     */
    @Override
    public List<BlogCategory> list(BlogCategory category) {
        QueryWrapper<BlogCategory> wrapper = new QueryWrapper<>(category);
        if (StringUtil.isNotNull(category.getBeginTime())) {
            wrapper.lambda().le(BlogCategory::getCreateTime, category.getBeginTime());
        }
        if (StringUtil.isNotNull(category.getEndTime())) {
            wrapper.lambda().ge(BlogCategory::getCreateTime, category.getEndTime());
        }
        return list(wrapper);
    }

    /**
     * 删除
     *
     * @param categoryIds
     * @return
     */
    @Override
    public boolean deleteCategory(List<Integer> categoryIds) {
        int count = articleService.count(new LambdaQueryWrapper<BlogArticle>().in(BlogArticle::getCategoryId,
                categoryIds));
        if (count >= 1) {
            throw new ServiceException("删除分类失败,该分类下有关联博文");
        }
        return removeByIds(categoryIds);
    }
}
