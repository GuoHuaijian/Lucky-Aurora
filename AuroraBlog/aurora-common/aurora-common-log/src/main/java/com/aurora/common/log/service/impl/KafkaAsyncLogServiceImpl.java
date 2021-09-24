package com.aurora.common.log.service.impl;

import com.alibaba.fastjson.JSON;
import com.aurora.common.log.service.AsyncLogService;
import com.aurora.common.rocketmq.producer.RocketMqProducer;
import com.aurora.rpc.system.domain.SysLog;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

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
    private RocketMqProducer rocketMqProducer;

    /**
     * 保存系统日志记录
     */
    @Async("asyncExecutor")
    @Override
    public void saveSysLog(SysLog sysLog) throws UnsupportedEncodingException {
        rocketMqProducer.sendLog(JSON.toJSONString(sysLog));
    }
}
