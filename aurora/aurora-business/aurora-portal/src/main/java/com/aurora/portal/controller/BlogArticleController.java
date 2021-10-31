package com.aurora.portal.controller;

import com.aurora.common.blog.domain.BlogArticle;
import com.aurora.common.blog.service.BlogArticleService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/portal/article")
public class BlogArticleController extends AbstractController {

    @Autowired
    private BlogArticleService articleService;

    @GetMapping("/list")
    public Result list() {
        startPage();
        List<BlogArticle> articles = articleService.list();
        return Result.success(getPageDate(articles));
    }
}
