package com.aurora.system.kafka;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.aurora.common.kafka.constant.ContainerFactoryConstant;
import com.aurora.common.kafka.constant.TopicConstant;
import com.aurora.system.domain.SysLog;
import com.aurora.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 16:44
 * @Version 1.0
 */
@Component
@Slf4j
public class LogConsumer {

    @Resource
    private SysLogService logService;

    @KafkaListener(topics = {TopicConstant.LOG_TOPIC_NAME}, containerFactory = ContainerFactoryConstant.LOG_CONTAINER_FACTORY_NAME)
    public void receiveLog(ConsumerRecord<?, ?> record) {
        if (StrUtil.isNotEmpty((String) record.value())) {
            SysLog sysLog = JSON.parseObject(record.value().toString(), SysLog.class);
            logService.saveLog(sysLog);
            log.info("消息队列消费消息： Topic:" + record.topic() + ",Message:" + record.value());
        }
    }
}
