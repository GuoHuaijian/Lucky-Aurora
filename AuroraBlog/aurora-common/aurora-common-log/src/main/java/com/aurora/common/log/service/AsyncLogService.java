package com.aurora.common.log.service;

import com.aurora.rpc.system.domain.SysLog;

import java.io.UnsupportedEncodingException;

/**
 * describe: 异步调用日志服务
 *
 * @Author Guo
 * @Date 2021/9/7 10:49
 * @Version 1.0
 */

public interface AsyncLogService {

    /**
     * 保存系统日志记录
     *
     * @param sysLog
     */
    void saveSysLog(SysLog sysLog) throws UnsupportedEncodingException;
}
