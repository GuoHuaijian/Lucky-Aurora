package com.aurora.admin.service;

import com.aurora.admin.domain.BlogComment;
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
public interface BlogCommentService extends IService<BlogComment> {

    /**
     * 查询评论
     *
     * @param id 评论主键
     * @return 评论
     */
    BlogComment selectBlogCommentById(String id);

    /**
     * 查询评论列表
     *
     * @param comment 评论
     * @return 评论集合
     */
    List<BlogComment> selectBlogCommentList(BlogComment comment);

    /**
     * 新增评论
     *
     * @param comment 评论
     * @return 结果
     */
    boolean insertBlogComment(BlogComment comment);

    /**
     * 修改评论
     *
     * @param comment 评论
     * @return 结果
     */
    boolean updateBlogComment(BlogComment comment);

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论主键集合
     * @return 结果
     */
    boolean deleteBlogCommentByIds(String[] ids);

    /**
     * 删除评论信息
     *
     * @param id 评论主键
     * @return 结果
     */
    boolean deleteBlogCommentById(String id);
}
