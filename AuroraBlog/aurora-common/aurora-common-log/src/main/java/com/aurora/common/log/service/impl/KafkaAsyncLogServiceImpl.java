package com.aurora.common.log.service.impl;

import com.aurora.common.log.service.AsyncLogService;
import com.aurora.common.rocketmq.constant.ConsumerGroupConstant;
import com.aurora.common.rocketmq.constant.TopicConstant;
import com.aurora.common.rocketmq.producer.RocketMqProducer;
import com.aurora.rpc.system.domain.SysLog;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
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
    private RocketMqProducer sendService;

    /**
     * 保存系统日志记录
     */
    @Async("asyncExecutor")
    @Override
    public void saveSysLog(SysLog sysLog) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
       sendService.send(sysLog,TopicConstant.LOG_TOPIC_NAME, ConsumerGroupConstant.LOG_GROUP_ID);
    }
}
