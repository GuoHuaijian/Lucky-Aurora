package com.aurora.common.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/25
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@ConditionalOnProperty(name = "aurora.rocketmq.producer.group")
@Component
@Slf4j
public class RocketMqProducer {

    @Resource
    private DefaultMQProducer defaultMQProducer;

    /**
     * 发送带tag的消息
     *
     * @param msg
     * @param topic
     * @param group
     * @param tag
     * @param <T>
     * @return
     */
    public <T> SendResult send(T msg, String topic, String group, String tag) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        if (StringUtils.isBlank(topic) || StringUtils.isBlank(group)) {
            new Throwable("发送方topic或者group不能为空");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Message message = new Message();
        message.setBody(JSON.toJSONBytes(msg));
        message.setTopic(topic);
        message.setTags(tag);
        message.setKeys(uuid);
        defaultMQProducer.setProducerGroup(group);
        SendResult result = defaultMQProducer.send(message);
        log.info("成功发送消息,消息内容为:{},返回值为:{}", message, result);
        return result;
    }

    /**
     * 发送不带tag的消息
     *
     * @param msg
     * @param topic
     * @param group
     * @param <T>
     * @return
     */
    public <T> SendResult send(T msg, String topic, String group) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        return this.send(msg, topic, group, null);
    }

}
