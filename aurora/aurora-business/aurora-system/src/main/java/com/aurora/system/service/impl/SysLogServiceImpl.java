package com.aurora.system.service.impl;

import com.aurora.system.domain.SysLog;
import com.aurora.system.mapper.SysLogMapper;
import com.aurora.system.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/30
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    /**
     * 保存操作日志
     *
     * @param sysLog
     * @return
     */
    @Override
    public boolean saveLog(SysLog sysLog) {
        sysLog.setOperTime(new Date());
        return save(sysLog);
    }
}
