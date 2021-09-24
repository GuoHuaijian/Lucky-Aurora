package com.aurora.common.rocketmq.consumer;

import com.aurora.common.rocketmq.constant.ConsumerGroupConstant;
import com.aurora.common.rocketmq.constant.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/24 15:12
 * @Version 1.0
 */
@Component
@Slf4j
public class RocketMqConsumer {

    @Resource
    private DefaultLitePullConsumer auroraLitePullConsumer;

    private DefaultLitePullConsumer consumer = auroraLitePullConsumer;

    /**
     * 日志处理
     */
    public List<MessageExt> receiveLog() throws MQClientException {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(TopicConstant.LOG_TOPIC_NAME, "*");
        consumer.setConsumerGroup(ConsumerGroupConstant.LOG_GROUP_ID);
        consumer.start();
        try {
            List<MessageExt> messageExts = consumer.poll();
            System.out.println("============================================" + messageExts);
            return messageExts;
        } finally {
            consumer.shutdown();
        }
    }

    /**
     * 博文添加处理
     */
    public List<MessageExt> receiveArticleAdd() throws MQClientException {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(TopicConstant.ARTICLE_ADD_TOPIC_NAME, "*");
        consumer.setConsumerGroup(ConsumerGroupConstant.ARTICLE_GROUP_ID);
        consumer.start();
        try {
            List<MessageExt> messageExts = consumer.poll();
            System.out.println("============================================" + messageExts);
            return messageExts;
        } finally {
            consumer.shutdown();
        }
    }

    /**
     * 博文更新处理
     */
    public void receiveArticleUpdate() throws UnsupportedEncodingException, MQClientException {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(TopicConstant.ARTICLE_UPDATE_TOPIC_NAME, "*");
        consumer.setConsumerGroup(ConsumerGroupConstant.ARTICLE_GROUP_ID);
        consumer.start();
        try {
            List<MessageExt> messageExts = consumer.poll();
            System.out.printf("%s%n", messageExts);
        } finally {
            consumer.shutdown();
        }
    }

    /**
     * 博文删除处理
     */
    public void receiveArticleDelete() throws UnsupportedEncodingException, MQClientException {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(TopicConstant.ARTICLE_DELETE_TOPIC_NAME, "*");
        consumer.setConsumerGroup(ConsumerGroupConstant.ARTICLE_GROUP_ID);
        consumer.start();
        try {
            List<MessageExt> messageExts = consumer.poll();
            System.out.printf("%s%n", messageExts);
        } finally {
            consumer.shutdown();
        }
    }
}
