package com.aurora.auth.service;

import com.aurora.auth.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhyd.oauth.model.AuthUser;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/11/2
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser getUserByName(String userName);

    /**
     * 新增第三方用户
     *
     * @param user
     * @return
     */
    SysUser addThirdUser(AuthUser user);

    /**
     * 更新第三方用户
     *
     * @param authUser
     * @param userId
     */
    void updateThirdUser(AuthUser authUser, Long userId);

    /**
     * 更新登录信息
     *
     * @param userId
     */
    void recordLoginInfo(Long userId);

}
