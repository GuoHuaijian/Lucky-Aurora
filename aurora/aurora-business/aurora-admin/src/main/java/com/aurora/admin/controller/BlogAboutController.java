package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogAbout;
import com.aurora.admin.service.BlogAboutService;
import com.aurora.common.core.utils.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/23
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/admin/about")
public class BlogAboutController extends AbstractController {

    @Autowired
    private BlogAboutService aboutService;

    /**
     * 查询关于我列表
     */
    @PreAuthorize("hasAuthority('admin:about:list')")
    @GetMapping("/list")
    public Result list(BlogAbout about) {
        startPage();
        List<BlogAbout> list = aboutService.selectBlogAboutList(about);
        return Result.success(getPageDate(list));
    }

    /**
     * 导出关于我列表
     */
    @PreAuthorize("hasAuthority('admin:about:export')")
    @Log(value = "关于我", LogType = LogType.EXPORT)
    @GetMapping("/export")
    public Result export(BlogAbout about) {
        List<BlogAbout> list = aboutService.selectBlogAboutList(about);
        ExcelUtil<BlogAbout> util = new ExcelUtil<>(BlogAbout.class);
        return util.exportExcel(list, "关于我数据");
    }

    /**
     * 获取关于我详细信息
     */
    @PreAuthorize("hasAuthority('admin:about:query')")
    @GetMapping(value = "/{aboutId}")
    public Result getInfo(@PathVariable("aboutId") Long aboutId) {
        return Result.success(aboutService.selectBlogAboutByAboutId(aboutId));
    }

    /**
     * 新增关于我
     */
    @PreAuthorize("hasAuthority('admin:about:add')")
    @Log(value = "关于我", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@RequestBody BlogAbout about) {
        return toResult(aboutService.insertBlogAbout(about));
    }

    /**
     * 修改关于我
     */
    @PreAuthorize("hasAuthority('admin:about:edit')")
    @Log(value = "关于我", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody BlogAbout about) {
        return toResult(aboutService.updateBlogAbout(about));
    }

    /**
     * 删除关于我
     */
    @PreAuthorize("hasAuthority('admin:about:remove')")
    @Log(value = "关于我", LogType = LogType.DELETE)
    @DeleteMapping("/{aboutIds}")
    public Result remove(@PathVariable Long[] aboutIds) {
        return toResult(aboutService.deleteBlogAboutByAboutIds(aboutIds));
    }
}
