package com.aurora.portal.controller;

import com.aurora.common.blog.domain.BlogTag;
import com.aurora.common.blog.service.BlogTagService;
import com.aurora.common.core.web.domain.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2022/1/19
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/tag")
public class BlogTagController {

    private final BlogTagService tagService;

    /**
     * 获取标签
     *
     * @return
     */
    @GetMapping("tags")
    public Result tagList() {
        List<BlogTag> tags = tagService.List();
        return Result.success(tags);
    }
}
