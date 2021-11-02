package com.aurora.auth.service.impl;

import com.aurora.auth.domain.SysUser;
import com.aurora.auth.mapper.SysUserMapper;
import com.aurora.auth.service.SysUserService;
import com.aurora.common.core.utils.DateUtil;
import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.core.utils.ip.IpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/11/2
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


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
