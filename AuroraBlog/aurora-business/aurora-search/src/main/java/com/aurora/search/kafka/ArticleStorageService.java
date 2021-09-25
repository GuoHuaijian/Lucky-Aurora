package com.aurora.search.kafka;

import com.aurora.common.rocketmq.constant.ConsumerGroupConstant;
import com.aurora.common.rocketmq.constant.TagConstant;
import com.aurora.common.rocketmq.constant.TopicConstant;
import com.aurora.common.rocketmq.consumer.RocketMqConsumer;
import com.aurora.search.service.EsService;
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
 * @Author Guo
 * @Date 2021/9/8 11:22
 * @Version 1.0
 */
@Component
@Slf4j
public class ArticleStorageService{

    @Resource
    private EsService esService;

    @Resource
    private RocketMqConsumer consumer;

    @PostConstruct
    public void onMessage() throws MQClientException {
        DefaultMQPushConsumer pushConsumer = consumer.buildConsumer(TopicConstant.ARTICLE_ADD_TOPIC_NAME, ConsumerGroupConstant.ARTICLE_GROUP_ID);
        pushConsumer.subscribe(TopicConstant.ARTICLE_ADD_TOPIC_NAME,TagConstant.ARTICLE_ADD);
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
                    log.info("MQ消息topic={}, tags={}, 消息内容={}", topic,tags,body);
                    esService.addIndex(body);
                } catch (Exception e) {
                    log.error("获取MQ消息内容异常{}",e);
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        pushConsumer.start();
    }

//    /**
//     * 博文更新处理
//     *
//     * @param record
//     */
//    @KafkaListener(topics = {TopicConstant.ARTICLE_UPDATE_TOPIC_NAME}, containerFactory = ContainerFactoryConstant.ARTICLE_CONTAINER_FACTORY_NAME)
//    public void articleUpdate(ConsumerRecord<?, ?> record) {
//        Optional message = Optional.ofNullable(record.value());
//        message.ifPresent(msg -> {
//            try {
//                esService.updateIndex(record);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            log.info("消息队列消费消息： Topic:" + record.topic() + ",Message:" + msg);
//        });
//    }
//
//    /**
//     * 博文删除处理
//     *
//     * @param record
//     */
//    @KafkaListener(topics = {TopicConstant.ARTICLE_DELETE_TOPIC_NAME}, containerFactory = ContainerFactoryConstant.ARTICLE_CONTAINER_FACTORY_NAME)
//    public void articleDelete(ConsumerRecord<?, ?> record) {
//        Optional message = Optional.ofNullable(record.value());
//        message.ifPresent(msg -> {
//            try {
//                esService.deleteIndex(JSONObject.toJSONString(msg));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            log.info("消息队列消费消息： Topic:" + record.topic() + ",Message:" + msg);
//        });
//    }
}
