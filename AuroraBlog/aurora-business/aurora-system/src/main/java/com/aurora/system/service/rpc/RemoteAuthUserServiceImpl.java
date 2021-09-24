package com.aurora.system.service.rpc;

import cn.hutool.core.bean.BeanUtil;
import com.aurora.rpc.system.RemoteAuthUserService;
import com.aurora.rpc.system.domain.AuthUser;
import com.aurora.system.domain.SysUser;
import com.aurora.system.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/6 11:40
 * @Version 1.0
 */
@DubboService(version = "1.0.0")
public class RemoteAuthUserServiceImpl implements RemoteAuthUserService {

    @Autowired
    private SysUserService userService;

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    @Override
    public AuthUser getUserByName(String userName) {
        SysUser user = userService.getUserByName(userName);
        AuthUser authUser = new AuthUser();
        BeanUtil.copyProperties(user, authUser);
        return authUser;
    }

    /**
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> getRolesByUserId(Long userId) {
        return userService.getRolesByUserId(userId);
    }

    /**
     * 根据用户id查询用户权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> getAuthsByUserId(Long userId) {
        return userService.getAuthsByUserId(userId);
    }
}
