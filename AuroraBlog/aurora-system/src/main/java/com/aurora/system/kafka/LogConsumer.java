package com.aurora.system.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aurora.common.kafka.constant.TopicConstant;
import com.aurora.system.domain.SysLog;
import com.aurora.system.service.SysLogService;
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
public class LogConsumer {

    @Resource
    private SysLogService logService;

    @KafkaListener(topics = {TopicConstant.LOG_TOPIC_NAME})
    public void receiveLog(ConsumerRecord< ? , ? > record){
        Object json = JSONObject.toJSON(record);
        SysLog sysLog = JSONObject.toJavaObject((JSON) json, SysLog.class);
        logService.saveLog(sysLog);
    }
}
