package com.aurora.common.log.service;

import com.aurora.rpc.system.domain.SysLog;
import com.aurora.rpc.system.domain.SysVisitLog;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * describe: 异步调用日志服务
 *
 * @Author Guo Huaijian
 * @Date 2021/9/7
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface AsyncLogService {

    /**
     * 保存操作日志记录
     *
     * @param sysLog
     * @throws MQBrokerException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQClientException
     */
    void saveLog(SysLog sysLog) throws MQBrokerException, RemotingException, InterruptedException, MQClientException;

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
