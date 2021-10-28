package com.aurora.system.service.impl;

import com.aurora.rpc.system.domain.SysOperateLog;
import com.aurora.system.mapper.SysOperateLogMapper;
import com.aurora.system.service.SysOperateLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogMapper, SysOperateLog> implements SysOperateLogService {

    @Autowired
    private SysOperateLogMapper operateLogMapper;

    /**
     * 保存操作日志
     *
     * @param operateLog
     * @return
     */
    @Override
    public boolean saveOperateLog(SysOperateLog operateLog) {
        return save(operateLog);
    }

    /**
     * 新增操作日志
     *
     * @param operateLog 操作日志对象
     */
    @Override
    public void insertOperateLog(SysOperateLog operateLog) {
        saveOperateLog(operateLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperateLog> selectOperateLogList(SysOperateLog operateLog) {
        return list(new QueryWrapper<>(operateLog));
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public boolean deleteOperateLogByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids));
    }

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperateLog selectOperateLogById(Long id) {
        return getById(id);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperateLog() {
        operateLogMapper.cleanOperateLog();
    }
}
