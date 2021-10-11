package com.aurora.common.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * describe: kafka消息队列生产者 配置类
 *
 * @Author Guo
 * @Date 2021/9/9 13:52
 * @Version 1.0
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String KafkaServers;

    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>(16);
        // kafka服务地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaServers);
        // 发生Retriable exceptions可重试异常时，重试发送消息次数，最大为Integer.MAX_VALUE
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        // 每当多个记录被发送到同一分区时，生产者将尝试将记录一起批量处理为更少的请求，默认值为16384(单位字节)
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 等待批量数据的时间，超过此时间，未达到批量发送基本单位也发送
        // Kafka客户端积累一定量的消息后会统一组装成一个批量消息发出，满足batch.size设置值或达到linger.ms超时时间都会发送
        props.put(ProducerConfig.LINGER_MS_CONFIG, 500);
        // 当客户端发送速率大于发送到broker速率时，会出现消费在发送方堆积，buffer.memory设置了可使用的buffer内存大小
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 设置自定义分区
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class);
        // 序列化
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate kafkaTemplate() {
        return new KafkaTemplate(producerFactory());
    }
}
