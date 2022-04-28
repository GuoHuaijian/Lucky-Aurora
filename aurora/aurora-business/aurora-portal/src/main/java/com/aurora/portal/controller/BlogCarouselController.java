package com.aurora.portal.controller;

import com.aurora.common.blog.domain.BlogCarousel;
import com.aurora.common.blog.service.BlogCarouselService;
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
 * @author Guo Huaijian
 * @date 2021/10/31
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequestMapping("/portal/carousel")
public class BlogCarouselController extends AbstractController {

    @Autowired
    private BlogCarouselService carouselService;

    /**
     * 查询轮播图列表
     */
    @GetMapping("/list")
    public Result list() {
        List<BlogCarousel> list = carouselService.list();
        return Result.success(list);
    }

}
