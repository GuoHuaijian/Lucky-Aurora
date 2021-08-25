package com.aurora.auth.service.impl;

import com.aurora.auth.domain.AuthUser;
import com.aurora.auth.mapper.AuthUserMapper;
import com.aurora.auth.service.AuthUserService;
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
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

    @Autowired
    private AuthUserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    @Override
    public AuthUser getUserByName(String userName) {
        LambdaQueryWrapper<AuthUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(AuthUser::getUsername, userName);
        AuthUser user = getOne(sysUserLambdaQueryWrapper);
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
}
