package com.aurora.common.rocketmq.producer;

import com.aurora.common.rocketmq.constant.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/24 14:54
 * @Version 1.0
 */
@Component
@Slf4j
public class RocketMqProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private DefaultMQProducer auroraMQProducer;


    /**
     * 日志处理
     *
     * @param sysLog
     */
    public void sendLog(String sysLog) throws UnsupportedEncodingException {
//        Message message = new Message();
//        message.setTopic(TopicConstant.LOG_TOPIC_NAME);
//        message.setBody(sysLog.getBytes("UTF-8"));
        rocketMQTemplate.setProducer(auroraMQProducer);
        rocketMQTemplate.syncSend(TopicConstant.LOG_TOPIC_NAME, sysLog);
//        rocketMQTemplate.send((org.springframework.messaging.Message<?>) message);
    }

    /**
     * 博文添加处理
     *
     * @param article
     */
    public void sendArticleAdd(String article) throws UnsupportedEncodingException {
//        Message message = new Message();
//        message.setTopic(TopicConstant.ARTICLE_ADD_TOPIC_NAME);
//        message.setBody(article.getBytes("UTF-8"));
//        rocketMQTemplate.send(message);
        rocketMQTemplate.setProducer(auroraMQProducer);
        rocketMQTemplate.syncSend(TopicConstant.ARTICLE_ADD_TOPIC_NAME, article);
    }

    /**
     * 博文更新处理
     *
     * @param article
     */
    public void sendArticleUpdate(String article) throws UnsupportedEncodingException {
        Message message = new Message();
        message.setTopic(TopicConstant.ARTICLE_UPDATE_TOPIC_NAME);
        message.setBody(article.getBytes("UTF-8"));
        rocketMQTemplate.send((org.springframework.messaging.Message<?>) message);
    }

    /**
     * 博文删除处理
     *
     * @param article
     */
    public void sendArticleDelete(String article) throws UnsupportedEncodingException {
        Message message = new Message();
        message.setTopic(TopicConstant.ARTICLE_DELETE_TOPIC_NAME);
        message.setBody(article.getBytes("UTF-8"));
        rocketMQTemplate.send((org.springframework.messaging.Message<?>) message);
    }
}
