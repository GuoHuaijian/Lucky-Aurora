package com.aurora.common.blog.service.impl;

import com.aurora.common.blog.domain.BlogArticle;
import com.aurora.common.blog.domain.BlogComment;
import com.aurora.common.blog.mapper.BlogCommentMapper;
import com.aurora.common.blog.service.BlogArticleService;
import com.aurora.common.blog.service.BlogCommentService;
import com.aurora.rpc.system.RemoteUserService;
import com.aurora.rpc.system.domain.User;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @version 1.0.0
 * @date 2021/10/16
 * @e-mail guohuaijian9527@gmail.com
 */
@Service
@RequiredArgsConstructor
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    @DubboReference(version = "1.0.0")
    private RemoteUserService userService;

    private final BlogArticleService articleService;

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
        List<BlogComment> comments = list(Wrappers.lambdaQuery(comment).orderByAsc(BlogComment::getCreateTime));
        addCommentInfo(comments);
        return comments.stream().sorted(Comparator.comparing(BlogComment::getCreateTime).reversed()).collect(Collectors.toList());
    }

    /**
     * 获取评论结构列表
     *
     * @param ownerId
     * @return
     */
    @Override
    public List<BlogComment> comments(Long ownerId) {
        BlogComment comment = new BlogComment();
        comment.setOwnerId(ownerId);
        List<BlogComment> comments = selectBlogCommentList(comment);
        return buildComment(comments);
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

    /**
     * 构建评论需要展示的信息
     *
     * @param comments
     */
    public void addCommentInfo(List<BlogComment> comments) {
        Set<Long> userIds = comments.stream().map(BlogComment::getObserverId).collect(Collectors.toSet());
        Set<Long> articleIds = comments.stream().map(BlogComment::getOwnerId).collect(Collectors.toSet());
        List<User> users = userService.getUsers(new ArrayList<>(userIds));
        List<BlogArticle> articles = articleService.listByIds(new ArrayList<>(articleIds));
        for (BlogComment comment : comments) {
            User user = users.stream().filter(u -> comment.getObserverId().equals(u.getUserId())).findFirst().get();
            comment.setName(user.getNickName());
            comment.setAvatar(user.getAvatar());
            if (Objects.nonNull(comment.getReplyId()) && comment.getReplyId() != 0) {
                BlogComment blogComment = comments.stream().filter(c -> comment.getReplyId().equals(c.getCommentId())).findFirst().get();
                comment.setReplyName(blogComment.getName());
            }
            if (Objects.nonNull(comment.getOwnerId())) {
                BlogArticle article =
                        articles.stream().filter(a -> comment.getOwnerId().equals(a.getArticleId())).findFirst().get();
                comment.setArticleTitle(article.getTitle());
            }
        }
    }

    /**
     * 构建评论
     *
     * @param comments
     */
    public List<BlogComment> buildComment(List<BlogComment> comments) {
        List<BlogComment> blogComments =
                comments.stream().filter(comment -> 0 == comment.getParentId()).collect(Collectors.toList());
        for (BlogComment comment : blogComments) {
            List<BlogComment> reply =
                    comments.stream().filter(comm -> comment.getCommentId().equals(comm.getParentId())).sorted(Comparator.comparing(BlogComment::getCreateTime)).collect(Collectors.toList());
            comment.setReply(reply);
        }
        return blogComments;
    }

}
