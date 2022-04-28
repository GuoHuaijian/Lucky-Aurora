package com.aurora.rpc.system;

import com.aurora.rpc.system.domain.SysOperateLog;
import com.aurora.rpc.system.domain.SysVisitLog;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/7
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface RemoteLogService {

    /**
     * 保存操作日志
     *
     * @param operateLog
     */
    void saveLog(SysOperateLog operateLog);

    /**
     * 保存访问日志
     *
     * @param visitLog
     */
    void saveLog(SysVisitLog visitLog);
}
