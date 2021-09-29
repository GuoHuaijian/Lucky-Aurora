package com.aurora.common.rocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/24
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Configuration
@EnableConfigurationProperties(RocketMqProperties.class)
@Slf4j
@ConditionalOnProperty(name = "aurora.rocketmq.name-server")
public class RocketMqAutoConfigure {

    @Resource
    private RocketMqProperties rocketMqProperties;

    /**
     * 生产者配置
     *
     * @return
     */
    @ConditionalOnProperty(name = "aurora.rocketmq.producer.group")
    @Bean(name = "defaultMQProducer")
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        RocketMqProperties.Producer producerConfig = rocketMqProperties.getProducer();
        String nameServer = rocketMqProperties.getNameServer();
        String groupName = producerConfig.getGroup();
        Assert.hasText(nameServer, "[rocketmq.name-server] must not be null");
        Assert.hasText(groupName, "[rocketmq.producer.group] must not be null");
        String accessChannel = rocketMqProperties.getAccessChannel();
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(nameServer);
        if (!StringUtils.isEmpty(accessChannel)) {
            producer.setAccessChannel(AccessChannel.valueOf(accessChannel));
        }
        producer.setSendMsgTimeout(producerConfig.getSendMessageTimeout());
        producer.setRetryTimesWhenSendFailed(producerConfig.getRetryTimesWhenSendFailed());
        producer.setRetryTimesWhenSendAsyncFailed(producerConfig.getRetryTimesWhenSendAsyncFailed());
        producer.setMaxMessageSize(producerConfig.getMaxMessageSize());
        producer.setCompressMsgBodyOverHowmuch(producerConfig.getCompressMessageBodyThreshold());
        producer.setRetryAnotherBrokerWhenNotStoreOK(producerConfig.isRetryNextServer());
        producer.start();
        log.info("初始化生产者完成");
        return producer;
    }

    /**
     * 消费者配置
     *
     * @return
     * @throws MQClientException
     */
    @ConditionalOnProperty(value = "aurora.rocketmq.consumer.group")
    @Bean(name = "defaultMQPushConsumer")
    public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
        RocketMqProperties.Consumer consumerConfig = rocketMqProperties.getConsumer();
        String nameServer = rocketMqProperties.getNameServer();
        String groupName = consumerConfig.getGroup();
        String topicName = consumerConfig.getTopic();
        Assert.hasText(nameServer, "[rocketmq.name-server] must not be null");
        Assert.hasText(groupName, "[rocketmq.consumer.group] must not be null");
        Assert.hasText(topicName, "[rocketmq.consumer.topic] must not be null");
        MessageModel messageModel = MessageModel.valueOf(consumerConfig.getMessageModel());
        int pullBatchSize = consumerConfig.getPullBatchSize();
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(groupName);
        defaultMQPushConsumer.setNamesrvAddr(nameServer);
        defaultMQPushConsumer.subscribe(topicName, "*");
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        defaultMQPushConsumer.setMessageModel(messageModel);
        defaultMQPushConsumer.setPullBatchSize(pullBatchSize);
        log.info("初始化消费者完成");
        return defaultMQPushConsumer;
    }
}
