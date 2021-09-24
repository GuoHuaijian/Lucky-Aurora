package com.aurora.system.kafka;

import com.aurora.common.rocketmq.consumer.RocketMqConsumer;
import com.aurora.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 16:44
 * @Version 1.0
 */
@Component
@Slf4j
public class LogConsumer {

    @Resource
    private SysLogService logService;

    @Resource
    private RocketMqConsumer rocketMqConsumer;

    public void receiveLog() throws MQClientException {
        List<MessageExt> messageExts = rocketMqConsumer.receiveLog();
        //logService.saveLog(messageExts);
        log.info("消息队列消费消息： Message:" + messageExts);

    }
}
