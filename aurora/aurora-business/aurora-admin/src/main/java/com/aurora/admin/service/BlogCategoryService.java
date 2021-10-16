package com.aurora.admin.service;

import com.aurora.admin.domain.BlogCategory;
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
     * 条件查询
     *
     * @param category
     * @return
     */
    List<BlogCategory> list(BlogCategory category);

    /**
     * 删除分类
     *
     * @param categoryIds
     * @return
     */
    boolean deleteCategory(List<Integer> categoryIds);


}
