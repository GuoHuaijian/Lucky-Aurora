package com.aurora.auth.service;

import com.aurora.auth.domain.SysUser;
import com.aurora.auth.domain.SysUserAuth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/11/2
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface SysUserAuthService extends IService<SysUserAuth> {

    /**
     * 通过第三方授权信息获取用户
     *
     * @param uuid   第三方系统的唯一ID
     * @param source 第三方用户来源
     * @return
     */
    SysUser getUserByAuth(String uuid, String source);

    /**
     * 通过授权id获取授权用户关联
     *
     * @param authId
     * @return
     */
    SysUserAuth getUserAuthByAuthId(Integer authId);
}
