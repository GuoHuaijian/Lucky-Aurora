package com.aurora.portal.controller;

import com.aurora.common.blog.domain.BlogArticle;
import com.aurora.common.blog.domain.BlogCategory;
import com.aurora.common.blog.domain.BlogFriend;
import com.aurora.common.blog.domain.BlogTag;
import com.aurora.common.blog.service.BlogArticleService;
import com.aurora.common.blog.service.BlogCategoryService;
import com.aurora.common.blog.service.BlogFriendService;
import com.aurora.common.blog.service.BlogTagService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/portal/article")
public class BlogArticleController extends AbstractController {

    private final BlogArticleService articleService;

    private final BlogTagService tagService;

    private final BlogCategoryService categoryService;

    private final BlogFriendService friendService;

    @GetMapping("list")
    public Result list() {
        startPage();
        List<BlogArticle> articles = articleService.list(new BlogArticle());
        return Result.success(getPageDate(articles));
    }

    @GetMapping("tags")
    public Result tagList() {
        List<BlogTag> tags = tagService.List();
        return Result.success(tags);
    }

    @GetMapping("categories")
    public Result categoryList() {
        List<BlogCategory> categories = categoryService.list();
        return Result.success(categories);
    }

    @GetMapping("friends")
    public Result friendList() {
        List<BlogFriend> friends = friendService.list();
        return Result.success(friends);
    }
}
