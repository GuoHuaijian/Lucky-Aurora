package com.aurora.admin.controller;

import com.aurora.admin.service.BlogArticleService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.core.web.page.PageDate;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private BlogArticleService articleService;

    /**
     * 获取文章列表
     *
     * @param title
     * @return
     */
    @Log(value = "文章列表", LogType = LogType.OTHER)
    @GetMapping("/list")
    @PreAuthorize("hasRole('admin')")
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

    @Log(value = "测试", LogType = LogType.OTHER)
    @PostMapping("/test/{id}")
    @PreAuthorize("hasRole('admin')")
    public String test(@PathVariable Integer id) {
        int i = 10 / 0;
        String s = String.valueOf(id);
        return s;
    }

}
