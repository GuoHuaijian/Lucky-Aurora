package com.aurora.rpc.system;

import com.aurora.rpc.system.domain.SysLog;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 10:09
 * @Version 1.0
 */
public interface RemoteLogService {

    /**
     * 保存日志
     *
     * @param log
     */
    void saveLog(SysLog log);
}
