package com.aurora.auth.service;

import com.aurora.auth.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhyd.oauth.model.AuthUser;

import java.util.Set;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/11/2
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
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
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    Set<String> getRolesByUserId(Long userId);

    /**
     * 根据用户id查询用户权限
     *
     * @param userId
     * @return
     */
    Set<String> getAuthsByUserId(Long userId);

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
