package com.aurora.system.service;

import com.aurora.system.domain.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/7
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
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
