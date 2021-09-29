package com.aurora.rpc.system;


import com.aurora.rpc.system.domain.AuthUser;

import java.util.List;

/**
 * describe: 登陆用户所需信息
 *
 * @Author Guo Huaijian
 * @Date 2021/9/6
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface RemoteAuthUserService {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    AuthUser getUserByName(String userName);

    /**
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    List<String> getRolesByUserId(Long userId);

    /**
     * 根据用户id查询用户权限
     *
     * @param userId
     * @return
     */
    List<String> getAuthsByUserId(Long userId);
}
