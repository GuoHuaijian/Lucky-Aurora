package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogCarousel;
import com.aurora.admin.service.BlogCarouselService;
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
@RequestMapping("/admin/carousel")
public class BlogCarouselController extends AbstractController {
    @Autowired
    private BlogCarouselService carouselService;

    /**
     * 查询轮播图列表
     */
    @PreAuthorize("hasAuthority('admin:carousel:list')")
    @GetMapping("/list")
    public Result list(BlogCarousel carousel) {
        startPage();
        List<BlogCarousel> list = carouselService.selectBlogCarouselList(carousel);
        return Result.success(getPageDate(list));
    }

    /**
     * 导出轮播图列表
     */
    @PreAuthorize("hasAuthority('admin:carousel:export')")
    @Log(value = "轮播图", LogType = LogType.EXPORT)
    @GetMapping("/export")
    public Result export(BlogCarousel carousel) {
        List<BlogCarousel> list = carouselService.selectBlogCarouselList(carousel);
        ExcelUtil<BlogCarousel> util = new ExcelUtil<>(BlogCarousel.class);
        return util.exportExcel(list, "轮播图数据");
    }

    /**
     * 获取轮播图详细信息
     */
    @PreAuthorize("hasAuthority('admin:carousel:query')")
    @GetMapping(value = "/{carouselId}")
    public Result getInfo(@PathVariable("carouselId") Long carouselId) {
        return Result.success(carouselService.selectBlogCarouselByCarouselId(carouselId));
    }

    /**
     * 新增轮播图
     */
    @PreAuthorize("hasAuthority('admin:carousel:add')")
    @Log(value = "轮播图", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@RequestBody BlogCarousel carousel) {
        return toResult(carouselService.insertBlogCarousel(carousel));
    }

    /**
     * 修改轮播图
     */
    @PreAuthorize("hasAuthority('admin:carousel:edit')")
    @Log(value = "轮播图", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody BlogCarousel carousel) {
        return toResult(carouselService.updateBlogCarousel(carousel));
    }

    /**
     * 删除轮播图
     */
    @PreAuthorize("hasAuthority('admin:carousel:remove')")
    @Log(value = "轮播图", LogType = LogType.DELETE)
    @DeleteMapping("/{carouselIds}")
    public Result remove(@PathVariable Long[] carouselIds) {
        return toResult(carouselService.deleteBlogCarouselByCarouselIds(carouselIds));
    }
}