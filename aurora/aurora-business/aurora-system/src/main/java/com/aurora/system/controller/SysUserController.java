package com.aurora.system.controller;

import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.common.constant.SystemConstants;
import com.aurora.system.domain.SysRole;
import com.aurora.system.domain.SysUser;
import com.aurora.system.service.SysRoleService;
import com.aurora.system.service.SysUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/30
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("hasAuthority('system:user:list')")
    @GetMapping("/list")
    public Result list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return Result.success(getPageDate(list));
    }

    @Log(value = "用户管理", LogType = LogType.EXPORT)
    @PreAuthorize("hasAuthority('system:user:export')")
    @GetMapping("/export")
    public void export(SysUser user) throws IOException {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        util.exportExcel(list, "用户数据");
    }

    @Log(value = "用户管理", LogType = LogType.IMPORT)
    @PreAuthorize("hasAuthority('system:user:import')")
    @PostMapping("/importData")
    public Result importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = SecurityUtil.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return Result.success(message);
    }

    @GetMapping("/importTemplate")
    public void importTemplate() throws IOException {
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        util.importTemplateExcel("用户数据");
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public Result getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        userService.checkUserDataScope(userId);
        Result result = Result.success();
        List<SysRole> roles = roleService.selectRoleAll();
        result.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        if (StringUtil.isNotNull(userId)) {
            result.put(Result.DATA_TAG, userService.selectUserById(userId));
            result.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return result;
    }

    /**
     * 新增用户
     */
    @PreAuthorize("hasAuthority('system:user:add')")
    @Log(value = "用户管理", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysUser user) {
        if (SystemConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return Result.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtil.isNotEmpty(user.getPhoneNumber())
                && SystemConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return Result.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtil.isNotEmpty(user.getEmail())
                && SystemConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return Result.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        return toResult(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("hasAuthority('system:user:edit')")
    @Log(value = "用户管理", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (StringUtil.isNotEmpty(user.getPhoneNumber())
                && SystemConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return Result.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtil.isNotEmpty(user.getEmail())
                && SystemConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return Result.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        return toResult(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("hasAuthority('system:user:remove')")
    @Log(value = "用户管理", LogType = LogType.DELETE)
    @DeleteMapping("/{userIds}")
    public Result remove(@PathVariable Long[] userIds) {
        if (ArrayUtils.contains(userIds, SecurityUtil.getUsername())) {
            return Result.error("当前用户不能删除");
        }
        return toResult(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("hasAuthority('system:user:resetPwd')")
    @Log(value = "用户管理", LogType = LogType.UPDATE)
    @PutMapping("/resetPwd")
    public Result resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        return toResult(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("hasAuthority('system:user:edit')")
    @Log(value = "用户管理", LogType = LogType.UPDATE)
    @PutMapping("/changeStatus")
    public Result changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        return toResult(userService.updateUserStatus(user));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping("/authRole/{userId}")
    public Result authRole(@PathVariable("userId") Long userId) {
        Result result = Result.success();
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        result.put("user", user);
        result.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return result;
    }

    /**
     * 用户授权角色
     */
    @PreAuthorize("hasAuthority('system:user:edit')")
    @Log(value = "用户管理", LogType = LogType.GRANT)
    @PutMapping("/authRole")
    public Result insertAuthRole(Long userId, Long[] roleIds) {
        userService.insertUserAuth(userId, roleIds);
        return Result.success();
    }
}
