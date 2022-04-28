package com.aurora.admin.controller;

import com.aurora.common.blog.domain.BlogFriend;
import com.aurora.common.blog.service.BlogFriendService;
import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/24
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequestMapping("/admin/friend")
public class BlogFriendController extends AbstractController {

    @Autowired
    private BlogFriendService friendService;

    /**
     * 查询友链列表
     */
    @PreAuthorize("hasAuthority('admin:friend:list')")
    @GetMapping("/list")
    public Result list(BlogFriend friend) {
        startPage();
        List<BlogFriend> list = friendService.selectBlogFriendList(friend);
        return Result.success(getPageDate(list));
    }

    /**
     * 导出友链列表
     */
    @PreAuthorize("hasAuthority('admin:friend:export')")
    @Log(value = "友链", LogType = LogType.EXPORT)
    @GetMapping("/export")
    public void export(BlogFriend friend) throws IOException {
        List<BlogFriend> list = friendService.selectBlogFriendList(friend);
        ExcelUtil<BlogFriend> util = new ExcelUtil<>(BlogFriend.class);
        util.exportExcel(list, "友链数据");
    }

    /**
     * 获取友链详细信息
     */
    @PreAuthorize("hasAuthority('admin:friend:query')")
    @GetMapping(value = "/{friendId}")
    public Result getInfo(@PathVariable("friendId") Long friendId) {
        return Result.success(friendService.selectBlogFriendByFriendId(friendId));
    }

    /**
     * 新增友链
     */
    @PreAuthorize("hasAuthority('admin:friend:add')")
    @Log(value = "友链", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@RequestBody BlogFriend friend) {
        return toResult(friendService.insertBlogFriend(friend));
    }

    /**
     * 修改友链
     */
    @PreAuthorize("hasAuthority('admin:friend:edit')")
    @Log(value = "友链", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody BlogFriend friend) {
        return toResult(friendService.updateBlogFriend(friend));
    }

    /**
     * 删除友链
     */
    @PreAuthorize("hasAuthority('admin:friend:remove')")
    @Log(value = "友链", LogType = LogType.DELETE)
    @DeleteMapping("/{friendIds}")
    public Result remove(@PathVariable Long[] friendIds) {
        return toResult(friendService.deleteBlogFriendByFriendIds(friendIds));
    }
}
