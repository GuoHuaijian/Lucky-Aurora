package com.aurora.common.log.service;

import com.aurora.rpc.system.domain.SysOperateLog;
import com.aurora.rpc.system.domain.SysVisitLog;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * describe: 异步调用日志服务
 *
 * @author Guo Huaijian
 * @date 2021/9/7
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface AsyncLogService {

    /**
     * 保存操作日志记录
     *
     * @param operateLog
     * @throws MQBrokerException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQClientException
     */
    void saveLog(SysOperateLog operateLog) throws MQBrokerException, RemotingException, InterruptedException, MQClientException;

    /**
     * 保存访问日志记录
     *
     * @param visitLog
     * @throws MQBrokerException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQClientException
     */
    void saveLog(SysVisitLog visitLog) throws MQBrokerException, RemotingException,
            InterruptedException, MQClientException;
}
