package com.aurora.common.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/24
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "aurora.rocketmq")
public class RocketMqProperties {

    /**
     * rocketMQ地址
     */
    private String nameServer;

    /**
     * 接入信道, values: LOCAL, CLOUD
     */
    private String accessChannel;

    private RocketMqProperties.Producer producer;

    /**
     * 配置是否启用监听器。
     * 在某些特殊情况下，如果您不想在容器启动时启用侦听器，
     * 配置模式是这样的:
     * rocketmq.consumer.listeners.<group-name>.<topic-name>.enabled=<boolean value, true or false>
     * 默认情况下，监听器是启用的。
     */
    private RocketMqProperties.Consumer consumer = new RocketMqProperties.Consumer();


    @Data
    public static class Producer {

        /**
         * 默认组名
         */
        public static final String DEFAULT_GROUP = "Default_Producer";

        /**
         * 生产的组名.
         */
        private String group;

        /**
         * 超时
         */
        private int sendMessageTimeout = 3000;

        /**
         * 压缩消息体阈值，即消息体大于4k将被默认压缩
         */
        private int compressMessageBodyThreshold = 1024 * 4;

        /**
         * 在同步模式中声明发送失败之前在内部执行的最大重试次数
         * 这可能会导致消息重复，这要由应用程序开发人员来解决。
         */
        private int retryTimesWhenSendFailed = 2;

        /**
         * 在异步模式中声明发送失败之前内部重试的最大次数。
         * 这可能会导致消息重复，这要由应用程序开发人员来解决。
         */
        private int retryTimesWhenSendAsyncFailed = 2;

        /**
         * 指示是否在内部发送失败时重试另一个代理。
         */
        private boolean retryNextServer = false;

        /**
         * 允许的最大消息大小(以字节为单位)。
         */
        private int maxMessageSize = 1024 * 1024 * 4;

    }

    @Data
    public static final class Consumer {

        /**
         * 默认组名
         */
        public static final String DEFAULT_GROUP = "Default_Consumer";

        /**
         * 默认组名
         */
        public static final String DEFAULT_TOPIC = "Default_Topic";

        /**
         * 消费者的组名。
         */
        private String group;

        /**
         * 使用者的主题名称。
         */
        private String topic;

        /**
         * 控制消息方式，如果想让所有订户都收到消息全部消息，广播是一个不错的选择。
         */
        private String messageModel = "CLUSTERING";

        /**
         * 控制如何选择消息。
         */
        private String selectorType = "TAG";

        /**
         * 控制可选择的消息。
         */
        private String selectorExpression = "*";

        /**
         * 每次提取的最大消息数。
         */
        private int pullBatchSize = 10;

    }
}
