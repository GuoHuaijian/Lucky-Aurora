package com.aurora.system.service.impl;

import com.aurora.system.domain.SysUser;
import com.aurora.system.mapper.SysUserMapper;
import com.aurora.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    @Override
    public SysUser getUserByName(String userName) {
        LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysUser::getUserName, userName);
        SysUser user = getOne(userLambdaQueryWrapper);
        return user;
    }

    /**
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> getRolesByUserId(Long userId) {
        return userMapper.getRolesByUserId(userId);
    }

    /**
     * 根据用户id查询用户权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> getAuthsByUserId(Long userId) {
        return userMapper.getAuthsByUserId(userId);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean addUser(SysUser user) {
        return save(user);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(SysUser user) {
        return updateById(user);
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public List<SysUser> getUserList() {
        return list();
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteUser(List<Long> ids) {
        return removeByIds(ids);
    }
}
