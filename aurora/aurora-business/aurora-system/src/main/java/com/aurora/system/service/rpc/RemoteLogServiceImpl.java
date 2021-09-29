package com.aurora.system.service.rpc;

import cn.hutool.core.bean.BeanUtil;
import com.aurora.rpc.system.RemoteLogService;
import com.aurora.rpc.system.domain.SysLog;
import com.aurora.system.service.SysLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/7
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@DubboService(version = "1.0.0", interfaceClass = RemoteLogService.class)
public class RemoteLogServiceImpl implements RemoteLogService {

    @Resource
    private SysLogService logService;

    /**
     * 保存日志
     *
     * @param log
     */
    @Override
    public void saveLog(SysLog log) {
        com.aurora.system.domain.SysLog sysLog = new com.aurora.system.domain.SysLog();
        BeanUtil.copyProperties(log, sysLog);
        logService.saveLog(sysLog);
    }
}
