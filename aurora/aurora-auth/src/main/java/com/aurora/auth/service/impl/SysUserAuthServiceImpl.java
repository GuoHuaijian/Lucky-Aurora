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
@RequiredArgsConstructor
public class SysUserAuthServiceImpl extends ServiceImpl<SysUserAuthMapper, SysUserAuth> implements SysUserAuthService {


    private final SysThirdAuthService thirdAuthService;

    private final SysUserService userService;

    /**
     * 通过第三方授权信息获取用户
     *
     * @param uuid   第三方系统的唯一ID
     * @param source 第三方用户来源
     * @return
     */
    @Override
    public SysUser getUserByAuth(String uuid, String source) {
        SysThirdAuth thirdAuth = thirdAuthService.getThirdAuth(uuid, source);
        SysUserAuth userAuth = getUserAuthByAuthId(thirdAuth.getAuthId());
        SysUser user = userService.getById(userAuth.getUserId());
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
