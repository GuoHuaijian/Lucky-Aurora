package com.aurora.common.rocketmq.config;

import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/24 14:16
 * @Version 1.0
 */
@Configuration
//@ConditionalOnClass({RocketMqConsumer.class, RocketMqProducer.class})
//@ConditionalOnProperty(prefix = "aurora.rocketmq")
@EnableConfigurationProperties(RocketMqProperties.class)
public class RocketMqAutoConfigure {

    @Resource
    private RocketMqProperties rocketMqProperties;

    public static final String PRODUCER_BEAN_NAME = "auroraMQProducer";
    public static final String CONSUMER_BEAN_NAME = "auroraLitePullConsumer";

    @Bean(PRODUCER_BEAN_NAME)
    public DefaultMQProducer defaultMQProducer(RocketMqProperties rocketMqProperties) {
        RocketMqProperties.Producer producerConfig = rocketMqProperties.getProducer();
        String nameServer = rocketMqProperties.getNameServer();
        String groupName = producerConfig.getGroup();
        Assert.hasText(nameServer, "[rocketmq.name-server] must not be null");
        Assert.hasText(groupName, "[rocketmq.producer.group] must not be null");

        String accessChannel = rocketMqProperties.getAccessChannel();

        String ak = rocketMqProperties.getProducer().getAccessKey();
        String sk = rocketMqProperties.getProducer().getSecretKey();
        boolean isEnableMsgTrace = rocketMqProperties.getProducer().isEnableMsgTrace();
        String customizedTraceTopic = rocketMqProperties.getProducer().getCustomizedTraceTopic();

        DefaultMQProducer producer = RocketMQUtil.createDefaultMQProducer(groupName, ak, sk, isEnableMsgTrace, customizedTraceTopic);

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

        return producer;
    }

    @Bean(CONSUMER_BEAN_NAME)
    public DefaultLitePullConsumer defaultLitePullConsumer(RocketMqProperties rocketMqProperties)
            throws MQClientException {
        RocketMqProperties.Consumer consumerConfig = rocketMqProperties.getConsumer();
        String nameServer = rocketMqProperties.getNameServer();
        String groupName = consumerConfig.getGroup();
        String topicName = consumerConfig.getTopic();
        Assert.hasText(nameServer, "[rocketmq.name-server] must not be null");
        Assert.hasText(groupName, "[rocketmq.consumer.group] must not be null");
        Assert.hasText(topicName, "[rocketmq.consumer.topic] must not be null");

        String accessChannel = rocketMqProperties.getAccessChannel();
        MessageModel messageModel = MessageModel.valueOf(consumerConfig.getMessageModel());
        SelectorType selectorType = SelectorType.valueOf(consumerConfig.getSelectorType());
        String selectorExpression = consumerConfig.getSelectorExpression();
        String ak = consumerConfig.getAccessKey();
        String sk = consumerConfig.getSecretKey();
        int pullBatchSize = consumerConfig.getPullBatchSize();

        DefaultLitePullConsumer litePullConsumer = RocketMQUtil.createDefaultLitePullConsumer(nameServer, accessChannel,
                groupName, topicName, messageModel, selectorType, selectorExpression, ak, sk, pullBatchSize);
        litePullConsumer.setEnableMsgTrace(consumerConfig.isEnableMsgTrace());
        litePullConsumer.setCustomizedTraceTopic(consumerConfig.getCustomizedTraceTopic());
        return litePullConsumer;
    }

    @Bean
    public RocketMQTemplate rocketMQTemplate() throws MQClientException {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(defaultMQProducer(rocketMqProperties));
        rocketMQTemplate.setConsumer(defaultLitePullConsumer(rocketMqProperties));
        return rocketMQTemplate;
    }

}
