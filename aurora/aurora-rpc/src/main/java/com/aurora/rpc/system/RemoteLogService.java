package com.aurora.rpc.system;

import com.aurora.rpc.system.domain.SysLog;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/7
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface RemoteLogService {

    /**
     * 保存日志
     *
     * @param log
     */
    void saveLog(SysLog log);
}
