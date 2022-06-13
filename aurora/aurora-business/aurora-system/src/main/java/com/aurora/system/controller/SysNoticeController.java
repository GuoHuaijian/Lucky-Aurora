package com.aurora.system.controller;

import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.domain.SysNotice;
import com.aurora.system.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends AbstractController {

    @Autowired
    private SysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @PreAuthorize("hasAuthority('system:notice:list')")
    @GetMapping("/list")
    public Result list(SysNotice notice) {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getPageResult(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("hasAuthority('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public Result getInfo(@PathVariable Long noticeId) {
        return Result.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("hasAuthority('system:notice:add')")
    @Log(value = "通知公告", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysNotice notice) {
        notice.setCreateBy(SecurityUtil.getUsername());
        return toResult(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("hasAuthority('system:notice:edit')")
    @Log(value = "通知公告", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysNotice notice) {
        notice.setUpdateBy(SecurityUtil.getUsername());
        return toResult(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("hasAuthority('system:notice:remove')")
    @Log(value = "通知公告", LogType = LogType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public Result remove(@PathVariable Long[] noticeIds) {
        return toResult(noticeService.deleteNoticeByIds(noticeIds));
    }
}
