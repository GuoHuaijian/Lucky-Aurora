package com.aurora.system.controller;

import com.aurora.common.core.utils.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.system.domain.SysLoginLog;
import com.aurora.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/system/loginLog")
public class SysLoginLogController extends AbstractController {

    @Autowired
    private SysLoginLogService loginLogService;

    @PreAuthorize("hasAuthority('system:loginlog:list')")
    @GetMapping("/list")
    public Result list(SysLoginLog loginLog) {
        startPage();
        List<SysLoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return Result.success(getPageDate(list));
    }

    @Log(value = "登录日志", LogType = LogType.EXPORT)
    @PreAuthorize("hasAuthority('system:loginlog:export')")
    @GetMapping("/export")
    public Result export(SysLoginLog loginLog) {
        List<SysLoginLog> list = loginLogService.selectLoginLogList(loginLog);
        ExcelUtil<SysLoginLog> util = new ExcelUtil<>(SysLoginLog.class);
        return util.exportExcel(list, "登录日志");
    }

    @PreAuthorize("hasAuthority('system:loginlog:remove')")
    @Log(value = "登录日志", LogType = LogType.DELETE)
    @DeleteMapping("/{infoIds}")
    public Result remove(@PathVariable Long[] infoIds) {
        return toResult(loginLogService.deleteLoginLogByIds(infoIds));
    }

    @PreAuthorize("hasAuthority('system:loginlog:remove')")
    @Log(value = "登录日志", LogType = LogType.CLEAN)
    @DeleteMapping("/clean")
    public Result clean() {
        loginLogService.cleanLoginLog();
        return Result.success();
    }
}