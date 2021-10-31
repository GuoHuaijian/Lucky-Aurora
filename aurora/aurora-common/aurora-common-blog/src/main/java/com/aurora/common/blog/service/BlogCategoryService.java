package com.aurora.common.blog.service;

import com.aurora.common.blog.domain.BlogCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface BlogCategoryService extends IService<BlogCategory> {

    /**
     * 查询分类
     *
     * @param categoryId 分类主键
     * @return 分类
     */
    BlogCategory selectBlogCategoryByCategoryId(Long categoryId);

    /**
     * 查询分类列表
     *
     * @param category 分类
     * @return 分类集合
     */
    List<BlogCategory> selectBlogCategoryList(BlogCategory category);

    /**
     * 新增分类
     *
     * @param category 分类
     * @return 结果
     */
    boolean insertBlogCategory(BlogCategory category);

    /**
     * 修改分类
     *
     * @param category 分类
     * @return 结果
     */
    boolean updateBlogCategory(BlogCategory category);

    /**
     * 批量删除分类
     *
     * @param categoryIds 需要删除的分类主键集合
     * @return 结果
     */
    boolean deleteBlogCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除分类信息
     *
     * @param categoryId 分类主键
     * @return 结果
     */
    boolean deleteBlogCategoryByCategoryId(Long categoryId);

}
