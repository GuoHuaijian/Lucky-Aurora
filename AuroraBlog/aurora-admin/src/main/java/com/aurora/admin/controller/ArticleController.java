package com.aurora.admin.controller;

import com.aurora.admin.service.ArticleService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.core.web.page.PageDate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
public class ArticleController extends AbstractController {

    @Resource
    private ArticleService articleService;

    /**
     * 获取文章列表
     *
     * @param title
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('article:list')")
    public Result listArticle(String title) {
        startPage();
        PageDate pageDate = getPageDate(articleService.getArticleList(title));
        return Result.success(pageDate);
    }

    @GetMapping("/info/{articleId}")
    @PreAuthorize("hasPermission('article:list')")
    public Result info(@PathVariable Integer articleId) {

        return Result.success();
    }

}
