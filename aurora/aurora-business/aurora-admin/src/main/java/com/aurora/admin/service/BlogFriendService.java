package com.aurora.admin.service;

import com.aurora.admin.domain.BlogFriend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/24
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface BlogFriendService extends IService<BlogFriend> {

    /**
     * 查询友链
     *
     * @param friendId 友链主键
     * @return 友链
     */
    BlogFriend selectBlogFriendByFriendId(Long friendId);

    /**
     * 查询友链列表
     *
     * @param friend 友链
     * @return 友链集合
     */
    List<BlogFriend> selectBlogFriendList(BlogFriend friend);

    /**
     * 新增友链
     *
     * @param friend 友链
     * @return 结果
     */
    boolean insertBlogFriend(BlogFriend friend);

    /**
     * 修改友链
     *
     * @param friend 友链
     * @return 结果
     */
    boolean updateBlogFriend(BlogFriend friend);

    /**
     * 批量删除友链
     *
     * @param friendIds 需要删除的友链主键集合
     * @return 结果
     */
    boolean deleteBlogFriendByFriendIds(Long[] friendIds);

    /**
     * 删除友链信息
     *
     * @param friendId 友链主键
     * @return 结果
     */
    boolean deleteBlogFriendByFriendId(Long friendId);
}
