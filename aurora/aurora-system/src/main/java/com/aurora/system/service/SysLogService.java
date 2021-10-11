package com.aurora.system.service;

import com.aurora.system.domain.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 10:01
 * @Version 1.0
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 保存操作日志
     *
     * @param sysLog
     * @return
     */
    boolean saveLog(SysLog sysLog);


}
