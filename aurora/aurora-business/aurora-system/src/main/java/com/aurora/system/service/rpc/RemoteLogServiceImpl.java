package com.aurora.system.service.rpc;

import cn.hutool.core.bean.BeanUtil;
import com.aurora.rpc.system.RemoteLogService;
import com.aurora.rpc.system.domain.SysLog;
import com.aurora.rpc.system.domain.SysVisitLog;
import com.aurora.system.service.SysLogService;
import com.aurora.system.service.SysVisitLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/7
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@DubboService(version = "1.0.0", interfaceClass = RemoteLogService.class)
public class RemoteLogServiceImpl implements RemoteLogService {

    @Resource
    private SysLogService logService;

    @Resource
    private SysVisitLogService visitLogService;

    /**
     * 保存操作日志
     *
     * @param log
     */
    @Override
    public void saveLog(SysLog log) {
        com.aurora.system.domain.SysLog sysLog = new com.aurora.system.domain.SysLog();
        BeanUtil.copyProperties(log, sysLog);
        logService.saveLog(sysLog);
    }

    /**
     * 保存访问日志
     *
     * @param visitLog
     */
    @Override
    public void saveLog(SysVisitLog visitLog) {
        com.aurora.system.domain.SysVisitLog log = new com.aurora.system.domain.SysVisitLog();
        BeanUtil.copyProperties(visitLog, log);
        visitLogService.saveVisitLog(log);
    }
}
