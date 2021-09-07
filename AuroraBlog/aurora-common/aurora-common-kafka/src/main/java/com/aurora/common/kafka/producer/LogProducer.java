package com.aurora.common.kafka.producer;

import com.aurora.common.kafka.constant.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 16:31
 * @Version 1.0
 */
@Component
@Slf4j
public class LogProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void SendLog(String sysLog){
        kafkaTemplate.send(TopicConstant.LOG_TOPIC_NAME,sysLog).addCallback(success -> {
          log.info("kafka:"+success);
        },fail -> {
           log.debug("kafka错误"+fail);
        });
    }
}
