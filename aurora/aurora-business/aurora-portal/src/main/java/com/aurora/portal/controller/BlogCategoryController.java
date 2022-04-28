package com.aurora.portal.controller;

import com.aurora.common.blog.domain.BlogCategory;
import com.aurora.common.blog.service.BlogCategoryService;
import com.aurora.common.core.web.domain.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2022/1/19
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/category")
public class BlogCategoryController {

    private final BlogCategoryService categoryService;

    /**
     * 获取分类
     *
     * @return
     */
    @GetMapping("categories")
    public Result categoryList() {
        List<BlogCategory> categories = categoryService.list();
        return Result.success(categories);
    }
}
