package com.aurora.system.controller;

import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.rpc.system.domain.SysOperateLog;
import com.aurora.system.service.SysOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/system/operateLog")
public class SysOperateLogController extends AbstractController {

    @Autowired
    private SysOperateLogService operateLogService;

    @PreAuthorize("hasAuthority('system:operatelog:list')")
    @GetMapping("/list")
    public Result list(SysOperateLog operateLog) {
        startPage();
        List<SysOperateLog> list = operateLogService.selectOperateLogList(operateLog);
        return Result.success(getPageDate(list));
    }

    @Log(value = "操作日志", LogType = LogType.EXPORT)
    @PreAuthorize("hasAuthority('system:operatelog:export')")
    @GetMapping("/export")
    public void export(SysOperateLog operateLog) throws IOException {
        List<SysOperateLog> list = operateLogService.selectOperateLogList(operateLog);
        ExcelUtil<SysOperateLog> util = new ExcelUtil<>(SysOperateLog.class);
        util.exportExcel(list, "操作日志");
    }

    @Log(value = "操作日志", LogType = LogType.DELETE)
    @PreAuthorize("hasAuthority('system:operatelog:remove')")
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids) {
        return toResult(operateLogService.deleteOperateLogByIds(ids));
    }

    @Log(value = "操作日志", LogType = LogType.CLEAN)
    @PreAuthorize("hasAuthority('system:operatelog:remove')")
    @DeleteMapping("/clean")
    public Result clean() {
        operateLogService.cleanOperateLog();
        return Result.success();
    }
}
