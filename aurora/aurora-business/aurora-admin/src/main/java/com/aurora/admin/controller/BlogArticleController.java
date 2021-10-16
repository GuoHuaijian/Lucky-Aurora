package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogArticle;
import com.aurora.admin.service.BlogArticleService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/article")
public class BlogArticleController extends AbstractController {

    @Autowired
    private BlogArticleService articleService;

    @GetMapping("/list")
    public Result list(BlogArticle article) {
        startPage();
        List<BlogArticle> articles = articleService.list(article);
        return Result.success(getPageDate(articles));
    }

    @GetMapping("/info/{articleId}")
    public Result info(@PathVariable Integer articleId) {
        BlogArticle article = articleService.getArticle(articleId);
        return Result.success(article);
    }

    @PostMapping("/save")
    public Result saveArticle(@RequestBody BlogArticle article) {
        return toResult(articleService.saveArticle(article));
    }

    @PutMapping("/update")
    public Result updateArticle(@RequestBody BlogArticle article) {
        return toResult(articleService.updateArticle(article));
    }

    @PutMapping("/update/status")
    public Result updateStatus(@RequestBody BlogArticle article) {
        return toResult(articleService.updateById(article));
    }

    @DeleteMapping("/delete")
    public Result deleteBatch(@RequestBody List<Integer> articleIds) {
        return toResult(articleService.deleteBatch(articleIds));
    }
}
