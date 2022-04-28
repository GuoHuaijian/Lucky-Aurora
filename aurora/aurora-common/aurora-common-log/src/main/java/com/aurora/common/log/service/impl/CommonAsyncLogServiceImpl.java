package com.aurora.common.log.service.impl;

import com.aurora.common.log.service.AsyncLogService;
import com.aurora.rpc.system.RemoteLogService;
import com.aurora.rpc.system.domain.SysOperateLog;
import com.aurora.rpc.system.domain.SysVisitLog;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * describe: 普通注解异步处理日志
 *
 * @author Guo Huaijian
 * @date 2021/9/8
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service
public class CommonAsyncLogServiceImpl implements AsyncLogService {

    @DubboReference(version = "1.0.0")
    private RemoteLogService remoteLogService;

    /**
     * 保存操作日志记录
     */
    @Async("asyncExecutor")
    @Override
    public void saveLog(SysOperateLog operateLog) {
        remoteLogService.saveLog(operateLog);
    }

    /**
     * 保存访问日志记录
     */
    @Async("asyncExecutor")
    @Override
    public void saveLog(SysVisitLog visitLog) {
        remoteLogService.saveLog(visitLog);
    }
}
