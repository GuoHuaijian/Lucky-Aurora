package com.aurora.portal.controller;

import com.aurora.common.blog.domain.BlogFriend;
import com.aurora.common.blog.service.BlogFriendService;
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
 * @Date 2022/1/19
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/friend")
public class BlogFriendController {

    private final BlogFriendService friendService;

    /**
     * 获取友链
     *
     * @return
     */
    @GetMapping("friends")
    public Result friendList() {
        List<BlogFriend> friends = friendService.list();
        return Result.success(friends);
    }
}
