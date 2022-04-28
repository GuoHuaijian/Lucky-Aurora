package com.aurora.common.blog.service;

import com.aurora.common.blog.domain.BlogCarousel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/16
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface BlogCarouselService extends IService<BlogCarousel> {

    /**
     * 查询轮播图
     *
     * @param carouselId 轮播图主键
     * @return 轮播图
     */
    BlogCarousel selectBlogCarouselByCarouselId(Long carouselId);

    /**
     * 查询轮播图列表
     *
     * @param carousel 轮播图
     * @return 轮播图集合
     */
    List<BlogCarousel> selectBlogCarouselList(BlogCarousel carousel);

    /**
     * 新增轮播图
     *
     * @param carousel 轮播图
     * @return 结果
     */
    boolean insertBlogCarousel(BlogCarousel carousel);

    /**
     * 修改轮播图
     *
     * @param carousel 轮播图
     * @return 结果
     */
    boolean updateBlogCarousel(BlogCarousel carousel);

    /**
     * 批量删除轮播图
     *
     * @param carouselIds 需要删除的轮播图主键集合
     * @return 结果
     */
    boolean deleteBlogCarouselByCarouselIds(Long[] carouselIds);

    /**
     * 删除轮播图信息
     *
     * @param carouselId 轮播图主键
     * @return 结果
     */
    boolean deleteBlogCarouselByCarouselId(Long carouselId);

}
