package com.aurora.admin.service.impl;

import com.aurora.admin.common.enums.ModuleEnum;
import com.aurora.admin.domain.Article;
import com.aurora.admin.domain.Category;
import com.aurora.admin.domain.vo.ArticleVO;
import com.aurora.admin.mapper.ArticleMapper;
import com.aurora.admin.service.ArticleService;
import com.aurora.admin.service.CategoryService;
import com.aurora.admin.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /**
     * 获取文章列表
     *
     * @param title
     * @return
     */
    @Override
    public List<ArticleVO> getArticleList(String title) {
        List<ArticleVO> articleList = baseMapper.listArticleVo(title);
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getType,
                ModuleEnum.ARTICLE.getValue()));
        // 封装ArticleVo
        Optional.ofNullable(articleList).ifPresent((articleVos ->
                articleVos.forEach(articleVo -> {
                    // 设置类别
                    articleVo.setCategoryListStr(categoryService.renderCategoryArr(articleVo.getCategoryId(), categoryList));
                    // 设置标签列表
                    articleVo.setTagList(tagService.listByLinkId(articleVo.getId(), ModuleEnum.ARTICLE.getValue()));
                })));
        return articleList;
    }
}
