//package com.aurora.system.controller;
//
//import com.aurora.common.core.utils.FileUploadUtil;
//import com.aurora.common.core.utils.StringUtil;
//import com.aurora.common.core.web.controller.AbstractController;
//import com.aurora.common.core.web.domain.Result;
//import com.aurora.common.log.annotation.Log;
//import com.aurora.common.log.enums.LogType;
//import com.aurora.common.security.utils.SecurityUtil;
//import com.aurora.system.constant.SystemConstants;
//import com.aurora.system.domain.SysUser;
//import com.aurora.system.service.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.token.TokenService;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * describe:
// *
// * @Author Guo Huaijian
// * @Date 2021/10/10
// * @E-mail guohuaijian9527@gmail.com
// * @Version 1.0.0
// */
//@RestController
//@RequestMapping("/system/user/profile")
//public class SysProfileController extends AbstractController {
//    @Autowired
//    private SysUserService userService;
//
//    @Autowired
//    private TokenService tokenService;
//
//    /**
//     * 个人信息
//     */
//    @GetMapping
//    public Result profile() {
//        LoginUser loginUser = getLoginUser();
//        SysUser user = loginUser.getUser();
//        Result result = Result.success(user);
//        result.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
//        return result;
//    }
//
//    /**
//     * 修改用户
//     */
//    @Log(value = "个人信息", LogType = LogType.UPDATE)
//    @PutMapping
//    public Result updateProfile(@RequestBody SysUser user) {
//        if (StringUtil.isNotEmpty(user.getPhoneNumber())
//                && SystemConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
//            return Result.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
//        }
//        if (StringUtil.isNotEmpty(user.getEmail())
//                && SystemConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
//            return Result.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
//        }
//        LoginUser loginUser = getLoginUser();
//        SysUser sysUser = loginUser.getUser();
//        user.setUserId(sysUser.getUserId());
//        user.setPassword(null);
//        if (userService.updateUserProfile(user)) {
//            // 更新缓存用户信息
//            sysUser.setNickName(user.getNickName());
//            sysUser.setPhoneNumber(user.getPhoneNumber());
//            sysUser.setEmail(user.getEmail());
//            sysUser.setSex(user.getSex());
//            tokenService.setLoginUser(loginUser);
//            return Result.success();
//        }
//        return Result.error("修改个人信息异常，请联系管理员");
//    }
//
//    /**
//     * 重置密码
//     */
//    @Log(value = "个人信息", LogType = LogType.UPDATE)
//    @PutMapping("/updatePwd")
//    public Result updatePwd(String oldPassword, String newPassword) {
//        LoginUser loginUser = getLoginUser();
//        String userName = loginUser.getUsername();
//        String password = loginUser.getPassword();
//        if (!SecurityUtil.matchesPassword(oldPassword, password)) {
//            return Result.error("修改密码失败，旧密码错误");
//        }
//        if (SecurityUtil.matchesPassword(newPassword, password)) {
//            return Result.error("新密码不能与旧密码相同");
//        }
//        if (userService.resetUserPwd(userName, SecurityUtil.encryptPassword(newPassword))) {
//            // 更新缓存用户密码
//            loginUser.getUser().setPassword(SecurityUtil.encryptPassword(newPassword));
//            tokenService.setLoginUser(loginUser);
//            return Result.success();
//        }
//        return Result.error("修改密码异常，请联系管理员");
//    }
//
//    /**
//     * 头像上传
//     */
//    @Log(value = "用户头像", LogType = LogType.UPDATE)
//    @PostMapping("/avatar")
//    public Result avatar(@RequestParam("avatarfile") MultipartFile file) {
//        if (!file.isEmpty()) {
//            LoginUser loginUser = getLoginUser();
//            String avatar = FileUploadUtil.upload(RuoYiConfig.getAvatarPath(), file);
//            if (userService.updateUserAvatar(loginUser.getUsername(), avatar)) {
//                Result ajax = Result.success();
//                ajax.put("imgUrl", avatar);
//                // 更新缓存用户头像
//                loginUser.getUser().setAvatar(avatar);
//                tokenService.setLoginUser(loginUser);
//                return ajax;
//            }
//        }
//        return Result.error("上传图片异常，请联系管理员");
//    }
//}
