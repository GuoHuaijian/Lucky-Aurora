package com.aurora.common.kafka.config;

import com.aurora.common.kafka.constant.ConsumerGroupConstant;
import com.aurora.common.kafka.constant.ContainerFactoryConstant;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * describe: kafka消息队列消费者 配置类
 *
 * @Author Guo
 * @Date 2021/9/9 13:29
 * @Version 1.0
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String KafkaServers;

    public Map<String, Object> consumerConfig(String consumerGroupId) {
        Map<String, Object> props = new HashMap<>(16);
        // kafka服务地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaServers);
        // 消费后是否自动提交 当前为false
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // 获取消息后提交偏移量的最大时间
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        // 超时时间，服务端没有收到心跳就会认为当前消费者失效
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        // 默认消费组
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        // earliest从头开始消费、latest获取最新消息
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // 序列化
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    public ConsumerFactory<String, String> consumerFactory(String consumerGroupId) {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(consumerGroupId));
    }

    /**
     * 个性化定义消费组 有多少消费组配多少个
     *
     * @return
     */
    @Bean(name = ContainerFactoryConstant.LOG_CONTAINER_FACTORY_NAME)
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(ConsumerGroupConstant.LOG_GROUP_ID));
        // 监听的线程数量，多个线程入库，数据库的id是自增的话，可能导致死锁，建议使用UUID
        factory.setConcurrency(1);
        // 消费者有两种消费模式，一种是kafka实例主动推送push模式，推送速度由kafka决定，很有可能导致消费端阻塞
        // 另一种就是 消费者主动拉取，poll模式
        factory.getContainerProperties().setPollTimeout(3000);
        // 当使用手动提交时必须设置ackMode为MANUAL,否则会报错No Acknowledgment available as an argument,
        // the listener container must have a MANUAL AckMode to populate the Acknowledgment.
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        // 下面两个条件哪个先满足，就会先使用那个
        // 达到1条数据的时候提交一次
        factory.getContainerProperties().setAckCount(1);
        // 10s提交一次
        factory.getContainerProperties().setAckTime(10000);
        return factory;
    }

    /**
     * 个性化定义消费组 有多少消费组配多少个
     *
     * @return
     */
    @Bean(name = ContainerFactoryConstant.ARTICLE_CONTAINER_FACTORY_NAME)
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaArticleListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(ConsumerGroupConstant.ARTICLE_GROUP_ID));
        // 监听的线程数量，多个线程入库，数据库的id是自增的话，可能导致死锁，建议使用UUID
        factory.setConcurrency(1);
        // 消费者有两种消费模式，一种是kafka实例主动推送push模式，推送速度由kafka决定，很有可能导致消费端阻塞
        // 另一种就是 消费者主动拉取，poll模式
        factory.getContainerProperties().setPollTimeout(3000);
        // 当使用手动提交时必须设置ackMode为MANUAL,否则会报错No Acknowledgment available as an argument,
        // the listener container must have a MANUAL AckMode to populate the Acknowledgment.
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        // 下面两个条件哪个先满足，就会先使用那个
        // 达到1条数据的时候提交一次
        factory.getContainerProperties().setAckCount(1);
        // 10s提交一次
        factory.getContainerProperties().setAckTime(10000);
        return factory;
    }

}
