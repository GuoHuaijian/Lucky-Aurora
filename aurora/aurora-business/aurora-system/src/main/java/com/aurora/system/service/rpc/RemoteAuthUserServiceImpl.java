package com.aurora.system.service.rpc;

import cn.hutool.core.bean.BeanUtil;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.rpc.system.RemoteAuthUserService;
import com.aurora.rpc.system.domain.AuthUser;
import com.aurora.system.constant.MenuConstants;
import com.aurora.system.domain.SysMenu;
import com.aurora.system.domain.SysUser;
import com.aurora.system.service.SysMenuService;
import com.aurora.system.service.SysRoleService;
import com.aurora.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/30
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@DubboService(version = "1.0.0")
public class RemoteAuthUserServiceImpl implements RemoteAuthUserService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuService menuService;

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
        // 超级管理员admin
        if (SecurityUtil.isAdmin(userId)) {
            List<String> roles = Lists.newArrayList();
            roleService.list().forEach(role -> roles.add(role.getRoleCode()));
            return roles;
        }
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
        // 超级管理员admin
        if (SecurityUtil.isAdmin(userId)) {
            List<String> permissions = Lists.newArrayList();
            menuService.list(new LambdaQueryWrapper<SysMenu>().ne(SysMenu::getMenuType, MenuConstants.TYPE_DIR))
                    .forEach(menu -> permissions.add(menu.getPerms()));
            return permissions;
        }
        return userService.getAuthsByUserId(userId);
    }
}
