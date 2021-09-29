package com.aurora.common.log.service;

import com.aurora.rpc.system.domain.SysLog;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

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
     * 保存系统日志记录
     *
     * @param sysLog
     */
    void saveSysLog(SysLog sysLog) throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException, MQClientException;
}
