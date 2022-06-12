package com.aurora.admin.controller;


import com.aurora.common.blog.domain.BlogArticle;
import com.aurora.common.blog.service.BlogArticleService;
import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/1/3
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/article")
public class BlogArticleController extends AbstractController {

    @Autowired
    private BlogArticleService articleService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("hasAuthority('admin:article:list')")
    @GetMapping("/list")
    public Result list(BlogArticle article) {
        startPage();
        List<BlogArticle> articles = articleService.list(article);
        return Result.success(getPageDate(articles));
    }

    /**
     * 导出文章列表
     */
    @PreAuthorize("hasAuthority('admin:article:export')")
    @Log(value = "文章", LogType = LogType.EXPORT)
    @GetMapping("/export")
    public void export(BlogArticle article) throws IOException {
        List<BlogArticle> list = articleService.list(article);
        ExcelUtil<BlogArticle> util = new ExcelUtil<>(BlogArticle.class);
        util.exportExcel(list, "文章数据");
    }

    /**
     * 获取文章详细信息
     */
    @PreAuthorize("hasAuthority('admin:article:query')")
    @GetMapping(value = "/{articleId}")
    public Result getInfo(@PathVariable("articleId") Integer articleId) {
        BlogArticle article = articleService.getArticle(articleId);
        return Result.success(article);
    }

    /**
     * 新增文章
     */
    @PreAuthorize("hasAuthority('admin:article:add')")
    @Log(value = "文章", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@RequestBody BlogArticle article) {
        return toResult(articleService.saveArticle(article));
    }

    /**
     * 修改文章
     */
    @PreAuthorize("hasAuthority('admin:article:edit')")
    @Log(value = "文章", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody BlogArticle article) {
        return toResult(articleService.updateArticle(article));
    }

    /**
     * 删除文章
     */
    @PreAuthorize("hasAuthority('admin:article:remove')")
    @Log(value = "文章", LogType = LogType.DELETE)
    @DeleteMapping("/{articleIds}")
    public Result remove(@PathVariable Long[] articleIds) {
        return toResult(articleService.deleteBatch(Arrays.asList(articleIds)));
    }
}
