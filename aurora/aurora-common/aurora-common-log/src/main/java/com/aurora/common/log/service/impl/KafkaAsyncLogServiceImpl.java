package com.aurora.common.log.service.impl;

import com.alibaba.fastjson.JSON;
import com.aurora.common.kafka.producer.KafkaProducer;
import com.aurora.common.log.service.AsyncLogService;
import com.aurora.rpc.system.domain.SysLog;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * describe: kafka异步处理日志
 *
 * @Author Guo
 * @Date 2021/9/8 9:42
 * @Version 1.0
 */
@Primary
@Service
public class KafkaAsyncLogServiceImpl implements AsyncLogService {

    @Resource
    private KafkaProducer logProducer;

    /**
     * 保存系统日志记录
     */
    @Async("asyncExecutor")
    @Override
    public void saveSysLog(SysLog sysLog) {
        logProducer.sendLog(JSON.toJSONString(sysLog));
    }
}
