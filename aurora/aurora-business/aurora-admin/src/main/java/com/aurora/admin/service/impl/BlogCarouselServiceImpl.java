package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogCarousel;
import com.aurora.admin.mapper.BlogCarouselMapper;
import com.aurora.admin.service.BlogCarouselService;
import com.aurora.common.core.utils.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class BlogCarouselServiceImpl extends ServiceImpl<BlogCarouselMapper, BlogCarousel> implements BlogCarouselService {

    /**
     * 查询轮播图
     *
     * @param carouselId 轮播图主键
     * @return 轮播图
     */
    @Override
    public BlogCarousel selectBlogCarouselByCarouselId(Long carouselId) {
        return getById(carouselId);
    }

    /**
     * 查询轮播图列表
     *
     * @param carousel 轮播图
     * @return 轮播图
     */
    @Override
    public List<BlogCarousel> selectBlogCarouselList(BlogCarousel carousel) {
        QueryWrapper<BlogCarousel> wrapper = new QueryWrapper<>(carousel);
        return list(wrapper);
    }

    /**
     * 新增轮播图
     *
     * @param carousel 轮播图
     * @return 结果
     */
    @Override
    public boolean insertBlogCarousel(BlogCarousel carousel) {
        carousel.setCreateTime(DateUtil.getNowDate());
        return save(carousel);
    }

    /**
     * 修改轮播图
     *
     * @param carousel 轮播图
     * @return 结果
     */
    @Override
    public boolean updateBlogCarousel(BlogCarousel carousel) {
        carousel.setUpdateTime(DateUtil.getNowDate());
        return updateById(carousel);
    }

    /**
     * 批量删除轮播图
     *
     * @param carouselIds 需要删除的轮播图主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogCarouselByCarouselIds(Long[] carouselIds) {
        return removeByIds(Arrays.asList(carouselIds));
    }

    /**
     * 删除轮播图信息
     *
     * @param carouselId 轮播图主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogCarouselByCarouselId(Long carouselId) {
        return removeById(carouselId);
    }
}
