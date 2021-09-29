package com.aurora.system.mq;

import com.alibaba.fastjson.JSON;
import com.aurora.common.rocketmq.constant.ConsumerGroupConstant;
import com.aurora.common.rocketmq.constant.TopicConstant;
import com.aurora.common.rocketmq.consumer.RocketMqConsumer;
import com.aurora.system.domain.SysLog;
import com.aurora.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/7
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Component
@Slf4j
public class LogConsumer {

    @Resource
    private SysLogService logService;

    @Resource
    private RocketMqConsumer consumer;

    @PostConstruct
    public void onMessage() throws MQClientException {
        DefaultMQPushConsumer pushConsumer = consumer.buildConsumer(TopicConstant.LOG_TOPIC_NAME,
                ConsumerGroupConstant.LOG_GROUP_ID);
        pushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            if (CollectionUtils.isEmpty(msgs)) {
                log.info("MQ接收消息为空，直接返回成功");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            for (MessageExt msg : msgs) {
                log.info("MQ接收到的消息为：" + msg.toString());
                try {
                    String topic = msg.getTopic();
                    String tags = msg.getTags();
                    String body = new String(msg.getBody(), "utf-8");
                    logService.saveLog(JSON.parseObject(body, SysLog.class));
                    log.info("MQ消息topic={}, tags={}, 消息内容={}", topic, tags, body);
                } catch (Exception e) {
                    log.error("获取MQ消息内容异常{}", e);
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        pushConsumer.start();
    }
}
