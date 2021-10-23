package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogFriend;
import com.aurora.admin.mapper.BlogFriendMapper;
import com.aurora.admin.service.BlogFriendService;
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
 * @Date 2021/10/24
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class BlogFriendServiceImpl extends ServiceImpl<BlogFriendMapper, BlogFriend> implements BlogFriendService {

    /**
     * 查询友链
     *
     * @param friendId 友链主键
     * @return 友链
     */
    @Override
    public BlogFriend selectBlogFriendByFriendId(Long friendId) {
        return getById(friendId);
    }

    /**
     * 查询友链列表
     *
     * @param friend 友链
     * @return 友链
     */
    @Override
    public List<BlogFriend> selectBlogFriendList(BlogFriend friend) {
        QueryWrapper<BlogFriend> wrapper = new QueryWrapper<>(friend);
        return list(wrapper);
    }

    /**
     * 新增友链
     *
     * @param friend 友链
     * @return 结果
     */
    @Override
    public boolean insertBlogFriend(BlogFriend friend) {
        return save(friend);
    }

    /**
     * 修改友链
     *
     * @param friend 友链
     * @return 结果
     */
    @Override
    public boolean updateBlogFriend(BlogFriend friend) {
        friend.setUpdateTime(DateUtil.getNowDate());
        return updateById(friend);
    }

    /**
     * 批量删除友链
     *
     * @param friendIds 需要删除的友链主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogFriendByFriendIds(Long[] friendIds) {
        return removeByIds(Arrays.asList(friendIds));
    }

    /**
     * 删除友链信息
     *
     * @param friendId 友链主键
     * @return 结果
     */
    @Override
    public boolean deleteBlogFriendByFriendId(Long friendId) {
        return removeById(friendId);
    }
}
