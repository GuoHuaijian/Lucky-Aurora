package com.aurora.system.service.impl;

import com.aurora.common.core.exception.ServiceException;
import com.aurora.common.core.utils.AssertUtil;
import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.common.constant.SystemConstants;
import com.aurora.system.domain.SysRole;
import com.aurora.system.domain.SysRoleMenu;
import com.aurora.system.domain.SysUser;
import com.aurora.system.domain.SysUserRole;
import com.aurora.system.mapper.SysRoleMapper;
import com.aurora.system.service.SysRoleMenuService;
import com.aurora.system.service.SysRoleService;
import com.aurora.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/9
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRoleMenuService roleMenuService;


    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return list(new QueryWrapper<>(role));
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        List<SysRole> userRoles = getRoleByUserId(userId);
        List<SysRole> roles = selectRoleAll();
        for (SysRole role : roles) {
            for (SysRole userRole : userRoles) {
                if (role.getRoleId().equals(userRole.getRoleId())) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> roles = getRoleByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : roles) {
            if (StringUtil.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleCode().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRoleAll() {
        return this.selectRoleList(new SysRole());
    }

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<Long> selectRoleListByUserId(Long userId) {
        List<SysUserRole> userRoles = userRoleService.list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        List<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return roleIds;
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public SysRole selectRoleById(Long roleId) {
        return getById(roleId);
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRole role) {
        Long roleId = StringUtil.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleName, role.getRoleName()));
        if (StringUtil.isNotNull(info) && !info.getRoleId().equals(roleId)) {
            return SystemConstants.NOT_UNIQUE;
        }
        return SystemConstants.UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRole role) {
        Long roleId = StringUtil.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleCode, role.getRoleCode()));
        if (StringUtil.isNotNull(info) && !info.getRoleId().equals(roleId)) {
            return SystemConstants.NOT_UNIQUE;
        }
        return SystemConstants.UNIQUE;
    }

    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    @Override
    public void checkRoleAllowed(SysRole role) {
        if (StringUtil.isNotNull(role.getRoleId()) && role.isAdmin()) {
            throw new ServiceException("不允许操作超级管理员角色");
        }
    }

    /**
     * 校验角色是否有数据权限
     *
     * @param roleId 角色id
     */
    @Override
    public void checkRoleDataScope(Long roleId) {
        if (!SysUser.isAdmin(SecurityUtil.getUserId())) {
            SysRole role = new SysRole();
            role.setRoleId(roleId);
            List<SysRole> roles = this.selectRoleList(role);
            AssertUtil.isNull(roles,"没有权限访问角色数据！");
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId) {
        int count = userRoleService.count(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, roleId));
        return count;
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean insertRole(SysRole role) {
        // 新增角色信息
        save(role);
        return insertRoleMenu(role);
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateRole(SysRole role) {
        // 修改角色信息
        updateById(role);
        // 删除角色与菜单关联
        roleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, role.getRoleId()));
        return insertRoleMenu(role);
    }

    /**
     * 修改角色状态
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public boolean updateRoleStatus(SysRole role) {
        return updateById(role);
    }


    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public boolean insertRoleMenu(SysRole role) {
        boolean flag = true;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : role.getMenuIds()) {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            flag = roleMenuService.saveBatch(list);
        }
        return flag;
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean deleteRoleById(Long roleId) {
        // 删除角色与菜单关联
        roleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        return removeById(roleId);
    }

    /**
     * 批量删除角色信息
     *
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean deleteRoleByIds(Long[] roleIds) {
        for (Long roleId : roleIds) {
            checkRoleAllowed(new SysRole(roleId));
            SysRole role = selectRoleById(roleId);
            AssertUtil.isTrue(roleId > 0,String.format("%1$s已分配,不能删除", role.getRoleName()));
        }
        // 删除角色与菜单关联
        roleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().in(SysRoleMenu::getRoleId, roleIds));
        return removeByIds(Arrays.asList(roleIds));
    }

    /**
     * 取消授权用户角色
     *
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    @Override
    public boolean deleteAuthUser(SysUserRole userRole) {
        return userRoleService.remove(new QueryWrapper<>(userRole));
    }

    /**
     * 批量取消授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要取消授权的用户数据ID
     * @return 结果
     */
    @Override
    public boolean deleteAuthUsers(Long roleId, Long[] userIds) {
        return userRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, roleId)
                .in(SysUserRole::getUserId, userIds));
    }

    /**
     * 批量选择授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    @Override
    public boolean insertAuthUsers(Long roleId, Long[] userIds) {
        // 新增用户与角色管理
        List<SysUserRole> list = Lists.newArrayList();
        for (Long userId : userIds) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return userRoleService.saveBatch(list);
    }


    public List<SysRole> getRoleByUserId(Long userId) {
        List<SysUserRole> userRoles = userRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId));
        List<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        List<SysRole> roles = list(new LambdaQueryWrapper<SysRole>().in(SysRole::getRoleId, roleIds));
        return roles;
    }
}
