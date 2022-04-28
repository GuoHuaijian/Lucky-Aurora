package com.aurora.auth.service.impl;

import com.aurora.auth.domain.SysUser;
import com.aurora.auth.mapper.SysUserMapper;
import com.aurora.auth.service.SysUserService;
import com.aurora.common.core.utils.DateUtil;
import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.core.utils.ip.IpUtil;
import com.aurora.common.security.utils.SecurityUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/11/2
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    @Override
    public SysUser getUserByName(String userName) {
        SysUser user = getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, userName));
        return user;
    }

    /**
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getRolesByUserId(Long userId) {
        // 超级管理员admin
        if (SecurityUtil.isAdmin(userId)) {
            return userMapper.getAllRoles();
        }
        return userMapper.getRolesByUserId(userId);
    }

    /**
     * 根据用户id查询用户权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getAuthsByUserId(Long userId) {
        // 超级管理员admin
        if (SecurityUtil.isAdmin(userId)) {
            return userMapper.getAllAuths();
        }
        return userMapper.getAuthsByUserId(userId);
    }

    /**
     * 新增第三方用户
     *
     * @param authUser
     * @return
     */
    @Override
    public SysUser addThirdUser(AuthUser authUser) {
        SysUser user = new SysUser();
        user.setUserType(3);
        user.setStatus(0);
        user = initUser(authUser, user);
        save(user);
        return user;
    }

    /**
     * 更新第三方用户
     *
     * @param authUser
     * @param userId
     */
    @Override
    public void updateThirdUser(AuthUser authUser, Long userId) {
        SysUser user = new SysUser();
        user.setUserId(userId);
        user = initUser(authUser, user);
        updateById(user);
    }

    /**
     * 记录登录信息
     */
    @Override
    public void recordLoginInfo(Long userId) {
        SysUser user = new SysUser();
        user.setUserId(userId);
        user.setLoginIp(IpUtil.getIpAddr(ServletUtil.getRequest()));
        user.setLoginTime(DateUtil.getNowDate());
        updateById(user);
    }

    /**
     * 初始化用户信息
     *
     * @param authUser
     * @param user
     * @return
     */
    public SysUser initUser(AuthUser authUser, SysUser user) {
        user.setUserName(authUser.getUsername());
        user.setNickName(authUser.getNickname());
        user.setAvatar(authUser.getAvatar());
        user.setEmail(authUser.getEmail());
        String code = authUser.getGender().getCode();
        if (AuthUserGender.MALE.getCode().equals(code)) {
            user.setSex(0);
        }
        if (AuthUserGender.FEMALE.getCode().equals(code)) {
            user.setSex(1);
        }
        if (AuthUserGender.UNKNOWN.getCode().equals(code)) {
            user.setSex(2);
        }
        return user;
    }
}
