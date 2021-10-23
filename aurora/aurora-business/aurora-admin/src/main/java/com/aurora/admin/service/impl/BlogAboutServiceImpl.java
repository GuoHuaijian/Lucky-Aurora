package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogAbout;
import com.aurora.admin.mapper.BlogAboutMapper;
import com.aurora.admin.service.BlogAboutService;
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
public class BlogAboutServiceImpl extends ServiceImpl<BlogAboutMapper, BlogAbout> implements BlogAboutService {

    /**
     * 查询关于我
     *
     * @param aboutId 关于我主键
     * @return 关于我
     */
    @Override
    public BlogAbout selectBlogAboutByAboutId(Long aboutId) {
        return getById(aboutId);
    }

    /**
     * 查询关于我列表
     *
     * @param about 关于我
     * @return 关于我
     */
    @Override
    public List<BlogAbout> selectBlogAboutList(BlogAbout about) {
        QueryWrapper<BlogAbout> wrapper = new QueryWrapper<>(about);
        return list(wrapper);
    }

    /**
     * 新增关于我
     *
     * @param about 关于我
     * @return 结果
     */
    @Override
    public boolean insertBlogAbout(BlogAbout about) {
        about.setCreateTime(DateUtil.getNowDate());
        return this.save(about);
    }

    /**
     * 修改关于我
     *
     * @param about 关于我
     * @return 结果
     */
    @Override
    public boolean updateBlogAbout(BlogAbout about) {
        about.setUpdateTime(DateUtil.getNowDate());
        return updateById(about);
    }

    /**
     * 批量删除关于我
     *
     * @param aboutIds 需要删除的关于我主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogAboutByAboutIds(Long[] aboutIds) {
        return removeByIds(Arrays.asList(aboutIds));
    }

    /**
     * 删除关于我信息
     *
     * @param aboutId 关于我主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogAboutByAboutId(Long aboutId) {
        return removeById(aboutId);
    }
}
