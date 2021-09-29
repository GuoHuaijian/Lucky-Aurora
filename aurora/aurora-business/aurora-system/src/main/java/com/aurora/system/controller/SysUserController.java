package com.aurora.system.controller;

import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.system.domain.SysUser;
import com.aurora.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/30
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("system/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService userService;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("add")
    public Result addUser(SysUser user) {
        return toResult(userService.addUser(user));
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("update")
    public Result updateUser(SysUser user) {
        return toResult(userService.updateUser(user));
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("list")
    public Result getUserList() {
        startPage();
        List<SysUser> userList = userService.getUserList();
        return Result.success(getPageDate(userList));
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @DeleteMapping("delete")
    public Result deleteUser(List<Long> ids) {
        return toResult(userService.deleteUser(ids));
    }
}
