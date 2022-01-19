package com.aurora.portal.controller;

import com.aurora.common.blog.domain.BlogArticle;
import com.aurora.common.blog.service.BlogArticleService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.VLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/31
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/article")
public class BlogArticleController extends AbstractController {

    private final BlogArticleService articleService;

    /**
     * 获取文章列表
     *
     * @param article
     * @return
     */
    @VLog("文章列表")
    @GetMapping("list")
    public Result list(BlogArticle article) {
        startPage();
        List<BlogArticle> articles = articleService.list(article);
        return Result.success(getPageDate(articles));
    }

    /**
     * 获取文章详细信息
     *
     * @param id
     * @return
     */
    @VLog(value = "查看文章",blogId = "#{id}")
    @GetMapping("/{id}")
    public Result list(@PathVariable("id") Integer id) {
        BlogArticle article = articleService.getArticle(id);
        return Result.success(article);
    }

}
