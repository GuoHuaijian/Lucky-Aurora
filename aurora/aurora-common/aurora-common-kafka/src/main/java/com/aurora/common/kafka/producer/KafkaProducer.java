package com.aurora.common.kafka.producer;

import com.aurora.common.kafka.constant.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 16:31
 * @Version 1.0
 */
@Component
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 日志处理
     *
     * @param sysLog
     */
    public void sendLog(String sysLog) {
        kafkaTemplate.send(TopicConstant.LOG_TOPIC_NAME, sysLog)
                .addCallback(success -> log.info("kafka:" + success), fail -> log.debug("kafka错误" + fail));
    }

    /**
     * 博文添加处理
     *
     * @param article
     */
    public void sendArticleAdd(String article) {
        kafkaTemplate.send(TopicConstant.ARTICLE_ADD_TOPIC_NAME, article)
                .addCallback(success -> log.info("kafka:" + success), fail -> log.debug("kafka错误" + fail));
    }

    /**
     * 博文更新处理
     *
     * @param article
     */
    public void sendArticleUpdate(String article) {
        kafkaTemplate.send(TopicConstant.ARTICLE_UPDATE_TOPIC_NAME, article)
                .addCallback(success -> log.info("kafka:" + success), fail -> log.debug("kafka错误" + fail));
    }

    /**
     * 博文删除处理
     *
     * @param article
     */
    public void sendArticleDelete(String article) {
        kafkaTemplate.send(TopicConstant.ARTICLE_DELETE_TOPIC_NAME, article).
                addCallback(success -> log.info("kafka:" + success), fail -> log.debug("kafka错误" + fail));
    }
}
