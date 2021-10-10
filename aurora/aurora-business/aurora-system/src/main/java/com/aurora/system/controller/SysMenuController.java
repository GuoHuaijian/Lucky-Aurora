package com.aurora.system.controller;

import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.system.constant.MenuConstants;
import com.aurora.system.constant.SystemConstants;
import com.aurora.system.domain.SysMenu;
import com.aurora.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends AbstractController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @PreAuthorize("@hasAuthority('system:menu:list')")
    @GetMapping("/list")
    public Result list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu, getUserId());
        return Result.success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @PreAuthorize("@hasAuthority('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public Result getInfo(@PathVariable Long menuId) {
        return Result.success(menuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeSelect")
    public Result treeselect(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu, getUserId());
        return Result.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeSelect/{roleId}")
    public Result roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        List<SysMenu> menus = menuService.selectMenuList(getUserId());
        Result result = Result.success();
        result.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        result.put("menus", menuService.buildMenuTreeSelect(menus));
        return result;
    }

    /**
     * 新增菜单
     */
    @PreAuthorize("@hasAuthority('system:menu:add')")
    @Log(value = "菜单管理", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysMenu menu) {
        if (SystemConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return Result.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (MenuConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtil.ishttp(menu.getPath())) {
            return Result.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        return toResult(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("@hasAuthority('system:menu:edit')")
    @Log(value = "菜单管理", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysMenu menu) {
        if (SystemConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (MenuConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtil.ishttp(menu.getPath())) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        return toResult(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@hasAuthority('system:menu:remove')")
    @Log(value = "菜单管理", LogType = LogType.DELETE)
    @DeleteMapping("/{menuId}")
    public Result remove(@PathVariable("menuId") Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return Result.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId)) {
            return Result.error("菜单已分配,不允许删除");
        }
        return toResult(menuService.deleteMenuById(menuId));
    }
}
