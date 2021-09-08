package com.aurora.common.kafka.utils;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.TopicPartitionInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/8 9:52
 * @Version 1.0
 */
@Component
public class KafkaUtil {

    @Value("${spring.kafka.bootstrap-servers}")
    private String springKafkaBootstrapServers;

    private AdminClient adminClient;

    @Resource
    private KafkaTemplate kafkaTemplate;


    /**
     * 初始化AdminClient
     */
    @PostConstruct
    private void initAdminClient() {
        Map<String, Object> props = new HashMap<>(1);
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, springKafkaBootstrapServers);
        adminClient = KafkaAdminClient.create(props);
    }

    /**
     * 新增topic，支持批量
     *
     * @param newTopics
     */
    public void createTopic(Collection<NewTopic> newTopics) {
        adminClient.createTopics(newTopics);
    }

    /**
     * 删除topic，支持批量
     *
     * @param topics
     */
    public void deleteTopic(Collection<String> topics) {
        adminClient.deleteTopics(topics);
    }

    /**
     * 获取指定topic的信息
     *
     * @param topics
     * @return
     */
    public String getTopicInfo(Collection<String> topics) {
        AtomicReference<String> info = new AtomicReference<>("");
        try {
            adminClient.describeTopics(topics).all().get().forEach((topic, description) -> {
                for (TopicPartitionInfo partition : description.partitions()) {
                    info.set(info + partition.toString() + "\n");
                }
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return info.get();
    }

    /**
     * 获取全部topic
     */
    public List<String> getAllTopic() {
        try {
            return adminClient.listTopics().listings().get().stream().map(TopicListing::name).collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    /**
     * 往topic中发送消息
     *
     * @param topic
     * @param message
     */
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

}
