package com.aurora.system.service;

import com.aurora.system.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
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
    List<String> getRolesByUserId(Long userId);

    /**
     * 根据用户id查询用户权限
     *
     * @param userId
     * @return
     */
    List<String> getAuthsByUserId(Long userId);

}
