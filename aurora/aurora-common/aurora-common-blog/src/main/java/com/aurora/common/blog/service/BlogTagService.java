package com.aurora.common.blog.service;

import com.aurora.common.blog.domain.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface BlogTagService extends IService<BlogTag> {

    /**
     * 查询标签
     *
     * @param tagId 标签主键
     * @return 标签
     */
    BlogTag selectBlogTagByTagId(Long tagId);

    /**
     * 查询标签列表
     *
     * @param tag 标签
     * @return 标签集合
     */
    List<BlogTag> selectBlogTagList(BlogTag tag);

    /**
     * 新增标签
     *
     * @param tag 标签
     * @return 结果
     */
    boolean insertBlogTag(BlogTag tag);

    /**
     * 修改标签
     *
     * @param tag 标签
     * @return 结果
     */
    boolean updateBlogTag(BlogTag tag);

    /**
     * 批量删除标签
     *
     * @param tagIds 需要删除的标签主键集合
     * @return 结果
     */
    boolean deleteBlogTagByTagIds(Long[] tagIds);

    /**
     * 删除标签信息
     *
     * @param tagId 标签主键
     * @return 结果
     */
    boolean deleteBlogTagByTagId(Long tagId);

    /**
     * 查询标签列表
     *
     * @return 标签集合
     */
    List<BlogTag> List();

}
