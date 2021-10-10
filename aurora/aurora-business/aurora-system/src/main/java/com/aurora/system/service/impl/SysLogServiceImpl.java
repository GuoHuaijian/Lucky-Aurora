package com.aurora.system.service.impl;

import com.aurora.system.domain.SysLog;
import com.aurora.system.mapper.SysLogMapper;
import com.aurora.system.service.SysLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private SysLogMapper logMapper;

    /**
     * 保存操作日志
     *
     * @param log
     * @return
     */
    @Override
    public boolean saveLog(SysLog log) {
        log.setOperTime(new Date());
        return save(log);
    }

    /**
     * 新增操作日志
     *
     * @param log 操作日志对象
     */
    @Override
    public void insertLog(SysLog log) {
        saveLog(log);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param log 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysLog> selectLogList(SysLog log) {
        return list(new QueryWrapper<>(log));
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public boolean deleteLogByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids));
    }

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysLog selectLogById(Long id) {
        return getById(id);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanLog() {
        logMapper.cleanLog();
    }
}
