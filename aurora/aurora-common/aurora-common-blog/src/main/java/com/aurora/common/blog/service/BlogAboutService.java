package com.aurora.common.blog.service;

import com.aurora.common.blog.domain.BlogAbout;
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
public interface BlogAboutService extends IService<BlogAbout> {

    /**
     * 查询关于我
     *
     * @param aboutId 关于我主键
     * @return 关于我
     */
    BlogAbout selectBlogAboutByAboutId(Long aboutId);

    /**
     * 查询关于我列表
     *
     * @param about 关于我
     * @return 关于我集合
     */
    List<BlogAbout> selectBlogAboutList(BlogAbout about);

    /**
     * 新增关于我
     *
     * @param about 关于我
     * @return 结果
     */
    boolean insertBlogAbout(BlogAbout about);

    /**
     * 修改关于我
     *
     * @param about 关于我
     * @return 结果
     */
    boolean updateBlogAbout(BlogAbout about);

    /**
     * 批量删除关于我
     *
     * @param aboutIds 需要删除的关于我主键集合
     * @return 结果
     */
    boolean deleteBlogAboutByAboutIds(Long[] aboutIds);

    /**
     * 删除关于我信息
     *
     * @param aboutId 关于我主键
     * @return 结果
     */
    boolean deleteBlogAboutByAboutId(Long aboutId);

}
