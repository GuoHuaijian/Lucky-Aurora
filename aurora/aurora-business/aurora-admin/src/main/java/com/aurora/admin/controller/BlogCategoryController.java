package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogCategory;
import com.aurora.admin.service.BlogCategoryService;
import com.aurora.common.core.utils.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * 查询分类列表
     */
    @PreAuthorize("hasAuthority('admin:category:list')")
    @GetMapping("/list")
    public Result list(BlogCategory category) {
        startPage();
        List<BlogCategory> list = categoryService.selectBlogCategoryList(category);
        return Result.success(getPageDate(list));
    }

    /**
     * 导出分类列表
     */
    @PreAuthorize("hasAuthority('admin:category:export')")
    @Log(value = "分类", LogType = LogType.EXPORT)
    @GetMapping("/export")
    public Result export(BlogCategory category) {
        List<BlogCategory> list = categoryService.selectBlogCategoryList(category);
        ExcelUtil<BlogCategory> util = new ExcelUtil<>(BlogCategory.class);
        return util.exportExcel(list, "分类数据");
    }

    /**
     * 获取分类详细信息
     */
    @PreAuthorize("hasAuthority('admin:category:query')")
    @GetMapping(value = "/{categoryId}")
    public Result getInfo(@PathVariable("categoryId") Long categoryId) {
        return Result.success(categoryService.selectBlogCategoryByCategoryId(categoryId));
    }

    /**
     * 新增分类
     */
    @PreAuthorize("hasAuthority('admin:category:add')")
    @Log(value = "分类", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@RequestBody BlogCategory category) {
        return toResult(categoryService.insertBlogCategory(category));
    }

    /**
     * 修改分类
     */
    @PreAuthorize("hasAuthority('admin:category:edit')")
    @Log(value = "分类", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody BlogCategory category) {
        return toResult(categoryService.updateBlogCategory(category));
    }

    /**
     * 删除分类
     */
    @PreAuthorize("hasAuthority('admin:category:remove')")
    @Log(value = "分类", LogType = LogType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public Result remove(@PathVariable Long[] categoryIds) {
        return toResult(categoryService.deleteBlogCategoryByCategoryIds(categoryIds));
    }

    /**
     * 选择下拉框
     */
    @GetMapping("/select")
    public Result select() {
        return Result.success(categoryService.list());
    }
}