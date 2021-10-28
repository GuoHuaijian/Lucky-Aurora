package com.aurora.system.service.rpc;

import com.aurora.rpc.system.RemoteLogService;
import com.aurora.rpc.system.domain.SysOperateLog;
import com.aurora.rpc.system.domain.SysVisitLog;
import com.aurora.system.service.SysOperateLogService;
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
    private SysOperateLogService logService;

    @Resource
    private SysVisitLogService visitLogService;

    /**
     * 保存操作日志
     *
     * @param operateLog
     */
    @Override
    public void saveLog(SysOperateLog operateLog) {
        logService.saveOperateLog(operateLog);
    }

    /**
     * 保存访问日志
     *
     * @param visitLog
     */
    @Override
    public void saveLog(SysVisitLog visitLog) {
        visitLogService.saveVisitLog(visitLog);
    }
}
