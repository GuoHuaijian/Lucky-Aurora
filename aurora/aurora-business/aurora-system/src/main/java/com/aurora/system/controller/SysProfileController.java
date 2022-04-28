package com.aurora.system.controller;

import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.rpc.file.RemoteFileService;
import com.aurora.system.common.constant.SystemConstants;
import com.aurora.system.domain.SysUser;
import com.aurora.system.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends AbstractController {

    @Autowired
    private SysUserService userService;

    @DubboReference(version = "1.0.0")
    private RemoteFileService fileService;

    /**
     * 个人信息
     */
    @GetMapping
    public Result profile() {
        SysUser user = userService.getById(SecurityUtil.getUserId());
        Result result = Result.success(user);
        result.put("roleGroup", userService.selectUserRoleGroup(user.getUserName()));
        return result;
    }

    /**
     * 修改用户
     */
    @Log(value = "个人信息", LogType = LogType.UPDATE)
    @PutMapping
    public Result updateProfile(@RequestBody SysUser user) {
        if (StringUtil.isNotEmpty(user.getPhoneNumber())
                && SystemConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return Result.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtil.isNotEmpty(user.getEmail())
                && SystemConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return Result.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        SysUser sysUser = userService.getById(SecurityUtil.getUserId());
        ;
        user.setUserId(sysUser.getUserId());
        user.setPassword(null);
        boolean flag = userService.updateUserProfile(user);
        return toResult(flag);
    }

    /**
     * 重置密码
     */
    @Log(value = "个人信息", LogType = LogType.UPDATE)
    @PutMapping("/updatePwd")
    public Result updatePwd(String oldPassword, String newPassword) {
        String userName = SecurityUtil.getUsername();
        String password = SecurityUtil.getSecurityUser().getPassword();
        if (!SecurityUtil.matchesPassword(oldPassword, password)) {
            return Result.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtil.matchesPassword(newPassword, password)) {
            return Result.error("新密码不能与旧密码相同");
        }
        boolean flag = userService.resetUserPwd(userName, SecurityUtil.encryptPassword(newPassword));
        return toResult(flag);
    }

    /**
     * 头像上传
     */
    @Log(value = "用户头像", LogType = LogType.UPDATE)
    @PostMapping("/avatar")
    public Result avatar(@RequestParam("avatarFile") MultipartFile file) {
        if (!file.isEmpty()) {
            String avatar = fileService.upload(file).get("url");
            if (userService.updateUserAvatar(SecurityUtil.getUsername(), avatar)) {
                Result result = Result.success();
                result.put("imgUrl", avatar);
                return result;
            }
            return Result.error("上传图片异常，请联系管理员");
        }
        return Result.error("文件不能为空");
    }
}
