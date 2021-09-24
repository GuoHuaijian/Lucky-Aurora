package com.aurora.search.kafka;

import com.aurora.common.rocketmq.consumer.RocketMqConsumer;
import com.aurora.search.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
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

    @Resource
    private RocketMqConsumer rocketMqConsumer;

    /**
     * 博文添加处理
     */
    public void articleAdd() throws MQClientException {
        List<MessageExt> messageExts = rocketMqConsumer.receiveArticleAdd();
        Optional message = Optional.ofNullable(messageExts);
        message.ifPresent(msg -> {
//            esService.addIndex(msg.toString());
            log.info("消息队列消费消息： Message:" + msg);
        });
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
