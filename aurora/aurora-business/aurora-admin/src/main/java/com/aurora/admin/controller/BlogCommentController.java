package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogComment;
import com.aurora.admin.service.BlogCommentService;
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
 * @Date 2021/10/23
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/admin/comment")
public class BlogCommentController extends AbstractController {

    @Autowired
    private BlogCommentService commentService;

    /**
     * 查询评论列表
     */
    @PreAuthorize("hasAuthority('admin:comment:list')")
    @GetMapping("/list")
    public Result list(BlogComment comment) {
        startPage();
        List<BlogComment> list = commentService.selectBlogCommentList(comment);
        return Result.success(getPageDate(list));
    }

    /**
     * 导出评论列表
     */
    @PreAuthorize("hasAuthority('admin:comment:export')")
    @Log(value = "评论", LogType = LogType.EXPORT)
    @GetMapping("/export")
    public Result export(BlogComment comment) {
        List<BlogComment> list = commentService.selectBlogCommentList(comment);
        ExcelUtil<BlogComment> util = new ExcelUtil<>(BlogComment.class);
        return util.exportExcel(list, "评论数据");
    }

    /**
     * 获取评论详细信息
     */
    @PreAuthorize("hasAuthority('admin:comment:query')")
    @GetMapping(value = "/{id}")
    public Result getInfo(@PathVariable("id") String id) {
        return Result.success(commentService.selectBlogCommentById(id));
    }

    /**
     * 新增评论
     */
    @PreAuthorize("hasAuthority('admin:comment:add')")
    @Log(value = "评论", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@RequestBody BlogComment comment) {
        return toResult(commentService.insertBlogComment(comment));
    }

    /**
     * 修改评论
     */
    @PreAuthorize("hasAuthority('admin:comment:edit')")
    @Log(value = "评论", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody BlogComment comment) {
        return toResult(commentService.updateBlogComment(comment));
    }

    /**
     * 删除评论
     */
    @PreAuthorize("hasAuthority('admin:comment:remove')")
    @Log(value = "评论", LogType = LogType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable String[] ids) {
        return toResult(commentService.deleteBlogCommentByIds(ids));
    }
}
