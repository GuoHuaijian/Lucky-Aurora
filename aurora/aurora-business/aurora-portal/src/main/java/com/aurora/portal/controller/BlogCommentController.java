package com.aurora.portal.controller;

import com.aurora.common.blog.domain.BlogComment;
import com.aurora.common.blog.service.BlogCommentService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2022/6/12
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequestMapping("/portal/comment")
@RequiredArgsConstructor
public class BlogCommentController extends AbstractController {

    private final BlogCommentService commentService;

    /**
     * 获取指定资源下的评论
     *
     * @param ownerId
     * @return
     */
    @GetMapping("/{ownerId}")
    public Result comments(@PathVariable Long ownerId){
        return Result.success(commentService.comments(ownerId));
    }

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @PostMapping("add")
    public Result save(@RequestBody BlogComment comment){
       return toResult(commentService.insertBlogComment(comment));
    }
}
