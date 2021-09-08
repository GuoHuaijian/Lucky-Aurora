package com.aurora.common.log.service.impl;

import com.aurora.common.log.service.AsyncLogService;
import com.aurora.rpc.system.RemoteLogService;
import com.aurora.rpc.system.domain.SysLog;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * describe: 普通注解异步处理日志
 *
 * @Author Guo
 * @Date 2021/9/8 9:44
 * @Version 1.0
 */
@Primary
@Service
public class CommonAsyncLogServiceImpl implements AsyncLogService {

    @DubboReference(version = "1.0.0")
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async("asyncExecutor")
    @Override
    public void saveSysLog(SysLog sysLog) {
        remoteLogService.saveLog(sysLog);
    }
}
