package com.aurora.search.mq;

import com.alibaba.fastjson.JSON;
import com.aurora.common.rocketmq.constant.ConsumerGroupConstant;
import com.aurora.common.rocketmq.constant.TagConstant;
import com.aurora.common.rocketmq.constant.TopicConstant;
import com.aurora.common.rocketmq.consumer.RocketMqConsumer;
import com.aurora.search.domain.Article;
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
 * @author Guo Huaijian
 * @date 2021/9/8
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Component
@Slf4j
public class ArticleConsumer {

    @Resource
    private EsService esService;

    @Resource
    private RocketMqConsumer consumer;

    @PostConstruct
    public void onMessage() throws MQClientException {
        DefaultMQPushConsumer pushConsumer = consumer.buildConsumer(TopicConstant.ARTICLE_ADD_TOPIC_NAME, ConsumerGroupConstant.ARTICLE_GROUP_ID);
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
                    log.info("MQ消息topic={}, tags={}, 消息内容={}", topic, tags, body);
                    if (TagConstant.ARTICLE_ADD.equals(tags)) {
                        esService.addIndex(body);
                    }
                    if (TagConstant.ARTICLE_UPDATE.equals(tags)) {
                        esService.updateIndex(body);
                    }
                    if (TagConstant.ARTICLE_DELETE.equals(tags)) {
                        esService.deleteIndex(JSON.parseObject(body, Article.class).getArticleId());
                    }
                } catch (Exception e) {
                    log.error("获取MQ消息内容异常{}", e);
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        pushConsumer.start();
    }
}
