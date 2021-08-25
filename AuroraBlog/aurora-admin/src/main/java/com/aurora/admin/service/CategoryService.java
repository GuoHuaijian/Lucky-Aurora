package com.aurora.admin.service;

import com.aurora.admin.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
public interface CategoryService extends IService<Category> {

    /**
     * 根据类别Id数组查询类别数组
     *
     * @param categoryIds
     * @param categoryList
     * @return
     */
    String renderCategoryArr(String categoryIds, List<Category> categoryList);


}
