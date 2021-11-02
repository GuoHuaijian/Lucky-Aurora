package com.aurora.auth.service.impl;

import com.aurora.auth.domain.SysThirdAuth;
import com.aurora.auth.domain.SysUser;
import com.aurora.auth.domain.SysUserAuth;
import com.aurora.auth.mapper.SysUserAuthMapper;
import com.aurora.auth.service.SysThirdAuthService;
import com.aurora.auth.service.SysUserAuthService;
import com.aurora.auth.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/11/2
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class SysUserAuthServiceImpl extends ServiceImpl<SysUserAuthMapper, SysUserAuth> implements SysUserAuthService {


    private final SysThirdAuthService thirdAuthService;

    private final SysUserService userService;

    /**
     * 通过第三方授权信息获取用户
     *
     * @param authUser
     * @return
     */
    @Override
    public SysUser getUserByAuth(AuthUser authUser) {
        SysThirdAuth thirdAuth = thirdAuthService.getThirdAuth(authUser.getUuid(), authUser.getSource());
        boolean flag = StringUtils.isEmpty(thirdAuth);
        // 第一次登录添加第三方用户
        if (flag) {
            Integer authId = thirdAuthService.addThirdAuth(authUser).getAuthId();
            Long userId = userService.addThirdUser(authUser).getUserId();
            save(new SysUserAuth(Integer.parseInt(String.valueOf(userId)), authId));
            thirdAuth = new SysThirdAuth();
            thirdAuth.setAuthId(authId);
        }
        SysUserAuth userAuth = getUserAuthByAuthId(thirdAuth.getAuthId());
        if (!flag) {
            thirdAuthService.updateThirdAuth(authUser, thirdAuth.getAuthId());
            userService.updateThirdUser(authUser, Long.parseLong(String.valueOf(userAuth.getUserId())));
        }
        SysUser user = userService.getById(userAuth.getUserId());
        userService.recordLoginInfo(user.getUserId());
        return user;
    }

    /**
     * 通过授权id获取授权用户关联
     *
     * @param authId
     * @return
     */
    @Override
    public SysUserAuth getUserAuthByAuthId(Integer authId) {
        SysUserAuth userAuth = getOne(new LambdaQueryWrapper<SysUserAuth>().eq(SysUserAuth::getAuthId, authId));
        return userAuth;
    }
}
