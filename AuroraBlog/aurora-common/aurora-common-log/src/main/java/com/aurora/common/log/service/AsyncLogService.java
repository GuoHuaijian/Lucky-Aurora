package com.aurora.common.log.service;

import com.alibaba.fastjson.JSON;
import com.aurora.common.kafka.producer.LogProducer;
import com.aurora.rpc.system.RemoteLogService;
import com.aurora.rpc.system.domain.SysLog;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * describe: 异步调用日志服务
 *
 * @Author Guo
 * @Date 2021/9/7 10:49
 * @Version 1.0
 */
@Service
public class AsyncLogService {

    @DubboReference(version = "1.0.0")
    private RemoteLogService remoteLogService;

    @Resource
    private LogProducer logProducer;

    /**
     * 保存系统日志记录
     */
    @Async("asyncExecutor")
    public void saveSysLog(SysLog sysLog) {
        //remoteLogService.saveLog(sysLog);
        logProducer.SendLog(JSON.toJSONString(sysLog));
    }
}
