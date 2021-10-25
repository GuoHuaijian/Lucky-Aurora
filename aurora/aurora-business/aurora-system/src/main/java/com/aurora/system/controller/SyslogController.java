package com.aurora.system.controller;

import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.system.domain.SysLog;
import com.aurora.system.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
@RequestMapping("/system/log")
public class SyslogController extends AbstractController {

    @Autowired
    private SysLogService logService;

    @PreAuthorize("hasAuthority('system:log:list')")
    @GetMapping("/list")
    public Result list(SysLog log) {
        startPage();
        List<SysLog> list = logService.selectLogList(log);
        return Result.success(getPageDate(list));
    }

    @Log(value = "操作日志", LogType = LogType.EXPORT)
    @PreAuthorize("hasAuthority('system:log:export')")
    @GetMapping("/export")
    public void export(SysLog log) throws IOException {
        List<SysLog> list = logService.selectLogList(log);
        ExcelUtil<SysLog> util = new ExcelUtil<>(SysLog.class);
        util.exportExcel(list, "操作日志");
    }

    @Log(value = "操作日志", LogType = LogType.DELETE)
    @PreAuthorize("hasAuthority('system:log:remove')")
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids) {
        return toResult(logService.deleteLogByIds(ids));
    }

    @Log(value = "操作日志", LogType = LogType.CLEAN)
    @PreAuthorize("hasAuthority('system:log:remove')")
    @DeleteMapping("/clean")
    public Result clean() {
        logService.cleanLog();
        return Result.success();
    }
}
