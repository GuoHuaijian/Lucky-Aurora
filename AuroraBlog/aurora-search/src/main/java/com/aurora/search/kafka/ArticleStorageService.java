package com.aurora.search.kafka;

import com.alibaba.fastjson.JSONObject;
import com.aurora.common.kafka.constant.ConsumerGroupConstant;
import com.aurora.common.kafka.constant.TopicConstant;
import com.aurora.search.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/8 11:22
 * @Version 1.0
 */
@Component
@Slf4j
public class ArticleStorageService {

    @Resource
    private EsService esService;

    /**
     * 博文添加处理
     *
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {TopicConstant.ARTICLE_ADD_TOPIC_NAME}, groupId = ConsumerGroupConstant.ARTICLE_GROUP_ID)
    public void articleAdd(ConsumerRecord<?, ?> record, Acknowledgment ack,
                           @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        message.ifPresent(msg -> {
            esService.addIndex(Arrays.asList(msg));
            ack.acknowledge();
            log.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
        });
    }

    /**
     * 博文更新处理
     *
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {TopicConstant.ARTICLE_UPDATE_TOPIC_NAME}, groupId = ConsumerGroupConstant.ARTICLE_GROUP_ID)
    public void articleUpdate(ConsumerRecord<?, ?> record, Acknowledgment ack,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        message.ifPresent(msg -> {
            try {
                esService.updateIndex(record);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ack.acknowledge();
            log.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
        });
    }

    /**
     * 博文删除处理
     *
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {TopicConstant.ARTICLE_DELETE_TOPIC_NAME}, groupId = ConsumerGroupConstant.ARTICLE_GROUP_ID)
    public void articleDelete(ConsumerRecord<?, ?> record, Acknowledgment ack,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        message.ifPresent(msg -> {
            try {
                esService.deleteIndex(JSONObject.toJSONString(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ack.acknowledge();
            log.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
        });
    }
}
