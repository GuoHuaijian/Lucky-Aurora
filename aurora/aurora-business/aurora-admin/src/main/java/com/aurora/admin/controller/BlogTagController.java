package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogTag;
import com.aurora.admin.service.BlogTagService;
import com.aurora.common.core.utils.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/admin/tag")
public class BlogTagController extends AbstractController {

    @Autowired
    private BlogTagService tagService;

    /**
     * 查询标签列表
     */
    @PreAuthorize("hasAuthority('admin:tag:list')")
    @GetMapping("/list")
    public Result list(BlogTag tag) {
        startPage();
        List<BlogTag> list = tagService.selectBlogTagList(tag);
        return Result.success(getPageDate(list));
    }

    /**
     * 导出标签列表
     */
    @PreAuthorize("hasAuthority('admin:tag:export')")
    @Log(value = "标签", LogType = LogType.EXPORT)
    @GetMapping("/export")
    public Result export(BlogTag tag) {
        List<BlogTag> list = tagService.selectBlogTagList(tag);
        ExcelUtil<BlogTag> util = new ExcelUtil<>(BlogTag.class);
        return util.exportExcel(list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    @PreAuthorize("hasAuthority('admin:tag:query')")
    @GetMapping(value = "/{tagId}")
    public Result getInfo(@PathVariable("tagId") Long tagId) {
        return Result.success(tagService.selectBlogTagByTagId(tagId));
    }

    /**
     * 新增标签
     */
    @PreAuthorize("hasAuthority('admin:tag:add')")
    @Log(value = "标签", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@RequestBody BlogTag tag) {
        return toResult(tagService.insertBlogTag(tag));
    }

    /**
     * 修改标签
     */
    @PreAuthorize("hasAuthority('admin:tag:edit')")
    @Log(value = "标签", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody BlogTag tag) {
        return toResult(tagService.updateBlogTag(tag));
    }

    /**
     * 删除标签
     */
    @PreAuthorize("hasAuthority('admin:tag:remove')")
    @Log(value = "标签", LogType = LogType.DELETE)
    @DeleteMapping("/{tagIds}")
    public Result remove(@PathVariable Long[] tagIds) {
        return toResult(tagService.deleteBlogTagByTagIds(tagIds));
    }

    /**
     * 获取下拉选择框
     */
    @GetMapping("/select")
    public Result select() {
        return Result.success(tagService.list());
    }
}