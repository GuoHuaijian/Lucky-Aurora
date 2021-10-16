package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogTag;
import com.aurora.admin.service.BlogTagService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/admin/tag")
public class BlogTagController extends AbstractController {

    @Autowired
    private BlogTagService tagService;


    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list(@RequestBody BlogTag tag) {
        startPage();
        List<BlogTag> tags = tagService.list(tag);
        return Result.success(getPageDate(tags));
    }

    /**
     * 获取下拉选择框
     *
     * @return
     */
    @GetMapping("/treeSelect")
    public Result treeSelect() {
        return Result.success(tagService.list());
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") String tagId) {
        return Result.success(tagService.getById(tagId));
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody BlogTag tag) {
        return toResult(tagService.save(tag));
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody BlogTag tag) {
        return toResult(tagService.updateById(tag));
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Integer> tagIds) {
        return toResult(tagService.deleteTag(tagIds));
    }
}
