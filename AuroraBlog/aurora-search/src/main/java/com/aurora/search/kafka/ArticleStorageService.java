package com.aurora.search.kafka;

import com.alibaba.fastjson.JSONObject;
import com.aurora.common.kafka.constant.ContainerFactoryConstant;
import com.aurora.common.kafka.constant.TopicConstant;
import com.aurora.search.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
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
     */
    @KafkaListener(topics = {TopicConstant.ARTICLE_ADD_TOPIC_NAME}, containerFactory = ContainerFactoryConstant.ARTICLE_CONTAINER_FACTORY_NAME)
    public void articleAdd(ConsumerRecord<?, ?> record) {
        Optional message = Optional.ofNullable(record.value());
        message.ifPresent(msg -> {
            esService.addIndex(msg.toString());
            log.info("消息队列消费消息： Topic:" + record.topic() + ",Message:" + msg);
        });
    }

    /**
     * 博文更新处理
     *
     * @param record
     */
    @KafkaListener(topics = {TopicConstant.ARTICLE_UPDATE_TOPIC_NAME}, containerFactory = ContainerFactoryConstant.ARTICLE_CONTAINER_FACTORY_NAME)
    public void articleUpdate(ConsumerRecord<?, ?> record) {
        Optional message = Optional.ofNullable(record.value());
        message.ifPresent(msg -> {
            try {
                esService.updateIndex(record);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("消息队列消费消息： Topic:" + record.topic() + ",Message:" + msg);
        });
    }

    /**
     * 博文删除处理
     *
     * @param record
     */
    @KafkaListener(topics = {TopicConstant.ARTICLE_DELETE_TOPIC_NAME}, containerFactory = ContainerFactoryConstant.ARTICLE_CONTAINER_FACTORY_NAME)
    public void articleDelete(ConsumerRecord<?, ?> record) {
        Optional message = Optional.ofNullable(record.value());
        message.ifPresent(msg -> {
            try {
                esService.deleteIndex(JSONObject.toJSONString(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("消息队列消费消息： Topic:" + record.topic() + ",Message:" + msg);
        });
    }
}
