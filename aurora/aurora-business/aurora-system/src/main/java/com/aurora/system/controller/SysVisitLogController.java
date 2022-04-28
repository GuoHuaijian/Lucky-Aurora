package com.aurora.system.controller;

import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.rpc.system.domain.SysVisitLog;
import com.aurora.system.service.SysVisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * describe:
 *
 * @author Guo
 * @date 2021/10/11 9:57
 * @version 1.0
 */
@RestController
@RequestMapping("/system/visitLog")
public class SysVisitLogController extends AbstractController {

    @Autowired
    private SysVisitLogService visitLogService;

    @PreAuthorize("hasAuthority('system:visitlog:list')")
    @GetMapping("/list")
    public Result list(SysVisitLog visitLog) {
        startPage();
        List<SysVisitLog> list = visitLogService.selectVisitLogList(visitLog);
        return Result.success(getPageDate(list));
    }

    @Log(value = "访问日志", LogType = LogType.EXPORT)
    @PreAuthorize("hasAuthority('system:visitlog:export')")
    @GetMapping("/export")
    public void export(SysVisitLog visitLog) throws IOException {
        List<SysVisitLog> list = visitLogService.selectVisitLogList(visitLog);
        ExcelUtil<SysVisitLog> util = new ExcelUtil<>(SysVisitLog.class);
        util.exportExcel(list, "访问日志");
    }

    @Log(value = "访问日志", LogType = LogType.DELETE)
    @PreAuthorize("hasAuthority('system:visitlog:remove')")
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids) {
        return toResult(visitLogService.deleteVisitLogByIds(ids));
    }

    @Log(value = "访问日志", LogType = LogType.CLEAN)
    @PreAuthorize("hasAuthority('system:visitlog:remove')")
    @DeleteMapping("/clean")
    public Result clean() {
        visitLogService.cleanVisitLog();
        return Result.success();
    }
}
