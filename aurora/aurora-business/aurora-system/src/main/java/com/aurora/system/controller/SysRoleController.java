package com.aurora.system.controller;

import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.system.common.constant.SystemConstants;
import com.aurora.system.domain.SysRole;
import com.aurora.system.domain.SysUser;
import com.aurora.system.domain.SysUserRole;
import com.aurora.system.service.SysRoleService;
import com.aurora.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends AbstractController {

    @Autowired
    private SysRoleService roleService;

//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private SysPermissionService permissionService;

    @Autowired
    private SysUserService userService;

    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/list")
    public Result list(SysRole role) {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getPageResult(list);
    }

    @Log(value = "角色管理", LogType = LogType.EXPORT)
    @PreAuthorize("hasAuthority('system:role:export')")
    @GetMapping("/export")
    public void export(SysRole role) throws IOException {
        List<SysRole> list = roleService.selectRoleList(role);
        ExcelUtil<SysRole> util = new ExcelUtil<>(SysRole.class);
        util.exportExcel(list, "角色数据");
    }

    /**
     * 根据角色编号获取详细信息
     */
    @PreAuthorize("hasAuthority('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public Result getInfo(@PathVariable Long roleId) {
        roleService.checkRoleDataScope(roleId);
        return Result.success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @PreAuthorize("hasAuthority('system:role:add')")
    @Log(value = "角色管理", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysRole role) {
        if (SystemConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return Result.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (SystemConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return Result.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        return toResult(roleService.insertRole(role));
    }

    /**
     * 修改保存角色
     */
    @PreAuthorize("hasAuthority('system:role:edit')")
    @Log(value = "角色管理", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        if (SystemConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return Result.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (SystemConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return Result.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        if (roleService.updateRole(role)) {
            // 更新缓存用户权限
            // TODO 待完善
//            LoginUser loginUser = getLoginUser();
//            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
//                loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
//                loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName()));
//                tokenService.setLoginUser(loginUser);
//            }
            return Result.success();
        }
        return Result.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 状态修改
     */
    @PreAuthorize("hasAuthority('system:role:edit')")
    @Log(value = "角色管理", LogType = LogType.UPDATE)
    @PutMapping("/changeStatus")
    public Result changeStatus(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        return toResult(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @PreAuthorize("hasAuthority('system:role:remove')")
    @Log(value = "角色管理", LogType = LogType.DELETE)
    @DeleteMapping("/{roleIds}")
    public Result remove(@PathVariable Long[] roleIds) {
        return toResult(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @PreAuthorize("hasAuthority('system:role:query')")
    @GetMapping("/optionSelect")
    public Result optionSelect() {
        return Result.success(roleService.selectRoleAll());
    }

    /**
     * 查询已分配用户角色列表
     */
    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/authUser/allocatedList")
    public Result allocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getPageResult(list);
    }

    /**
     * 查询未分配用户角色列表
     */
    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/authUser/unallocatedList")
    public Result unallocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUnallocatedList(user);
        return getPageResult(list);
    }

    /**
     * 取消授权用户
     */
    @PreAuthorize("hasAuthority('system:role:edit')")
    @Log(value = "角色管理", LogType = LogType.GRANT)
    @PutMapping("/authUser/cancel")
    public Result cancelAuthUser(@RequestBody SysUserRole userRole) {
        return toResult(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权用户
     */
    @PreAuthorize("hasAuthority('system:role:edit')")
    @Log(value = "角色管理", LogType = LogType.GRANT)
    @PutMapping("/authUser/cancelAll")
    public Result cancelAuthUserAll(Long roleId, Long[] userIds) {
        return toResult(roleService.deleteAuthUsers(roleId, userIds));
    }

    /**
     * 批量选择用户授权
     */
    @PreAuthorize("hasAuthority('system:role:edit')")
    @Log(value = "角色管理", LogType = LogType.GRANT)
    @PutMapping("/authUser/selectAll")
    public Result selectAuthUserAll(Long roleId, Long[] userIds) {
        return toResult(roleService.insertAuthUsers(roleId, userIds));
    }
}
