package com.aurora.common.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/24
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@ConditionalOnProperty(name = "aurora.rocketmq.consumer.group")
@Component
@Slf4j
public class RocketMqConsumer {

    @Resource
    private DefaultMQPushConsumer defaultMQPushConsumer;


    public DefaultMQPushConsumer buildConsumer() {
        return defaultMQPushConsumer;
    }

    public DefaultMQPushConsumer buildConsumer(String topic, String consumerGroup) throws MQClientException {
        defaultMQPushConsumer.subscribe(topic, "*");
        defaultMQPushConsumer.setConsumerGroup(consumerGroup);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        return defaultMQPushConsumer;
    }

}
