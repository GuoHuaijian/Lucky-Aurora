package com.aurora.common.blog.service.impl;

import com.aurora.common.blog.domain.BlogComment;
import com.aurora.common.blog.mapper.BlogCommentMapper;
import com.aurora.common.blog.service.BlogCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/16
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    /**
     * 查询评论
     *
     * @param id 评论主键
     * @return 评论
     */
    @Override
    public BlogComment selectBlogCommentById(String id) {
        return getById(id);
    }

    /**
     * 查询评论列表
     *
     * @param comment 评论
     * @return 评论
     */
    @Override
    public List<BlogComment> selectBlogCommentList(BlogComment comment) {
        return list(new QueryWrapper<>(comment));
    }

    /**
     * 新增评论
     *
     * @param comment 评论
     * @return 结果
     */
    @Override
    public boolean insertBlogComment(BlogComment comment) {
        return save(comment);
    }

    /**
     * 修改评论
     *
     * @param comment 评论
     * @return 结果
     */
    @Override
    public boolean updateBlogComment(BlogComment comment) {
        return updateById(comment);
    }

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogCommentByIds(String[] ids) {
        return removeByIds(Arrays.asList(ids));
    }

    /**
     * 删除评论信息
     *
     * @param id 评论主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogCommentById(String id) {
        return removeById(id);
    }
}
