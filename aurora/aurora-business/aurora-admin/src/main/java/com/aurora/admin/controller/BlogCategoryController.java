package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogCategory;
import com.aurora.admin.service.BlogCategoryService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/17
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/admin/category")
public class BlogCategoryController extends AbstractController {

    @Resource
    private BlogCategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestBody BlogCategory category) {
        startPage();
        List<BlogCategory> categories = categoryService.list(category);
        return Result.success(getPageDate(categories));
    }

    /**
     * 选择下拉框
     */
    @RequestMapping("/select")
    public Result treeSelect() {
        return Result.success(categoryService.list());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer categoryId) {
        return Result.success(categoryService.getById(categoryId));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody BlogCategory category) {
        return toResult(categoryService.save(category));
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody BlogCategory category) {
        return toResult(categoryService.updateById(category));
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public Result delete(List<Integer> categoryIds) {
        return toResult(categoryService.deleteCategory(categoryIds));
    }
}
