package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogArticleTag;
import com.aurora.admin.domain.BlogTag;
import com.aurora.admin.mapper.BlogTagMapper;
import com.aurora.admin.service.BlogArticleTagService;
import com.aurora.admin.service.BlogTagService;
import com.aurora.common.core.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    private BlogArticleTagService articleTagService;

    /**
     * 查询标签
     *
     * @param tagId 标签主键
     * @return 标签
     */
    @Override
    public BlogTag selectBlogTagByTagId(Long tagId) {
        return getById(tagId);
    }

    /**
     * 查询标签列表
     *
     * @param tag 标签
     * @return 标签
     */
    @Override
    public List<BlogTag> selectBlogTagList(BlogTag tag) {
        return list(new QueryWrapper<>(tag));
    }

    /**
     * 新增标签
     *
     * @param tag 标签
     * @return 结果
     */
    @Override
    public boolean insertBlogTag(BlogTag tag) {
        return save(tag);
    }

    /**
     * 修改标签
     *
     * @param tag 标签
     * @return 结果
     */
    @Override
    public boolean updateBlogTag(BlogTag tag) {
        return updateById(tag);
    }

    /**
     * 批量删除标签
     *
     * @param tagIds 需要删除的标签主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogTagByTagIds(Long[] tagIds) {
        int count = articleTagService.count(new LambdaQueryWrapper<BlogArticleTag>().in(BlogArticleTag::getTagId,
                tagIds));
        if (count >= 1) {
            throw new ServiceException("删除标签失败,该标签下有关联博文");
        }
        return removeByIds(Arrays.asList(tagIds));
    }

    /**
     * 删除标签信息
     *
     * @param tagId 标签主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogTagByTagId(Long tagId) {
        int count = articleTagService.count(new LambdaQueryWrapper<BlogArticleTag>().eq(BlogArticleTag::getTagId,
                tagId));
        if (count >= 1) {
            throw new ServiceException("删除标签失败,该标签下有关联博文");
        }
        return removeById(tagId);
    }
}
