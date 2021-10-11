package com.aurora.system.service.impl;

import com.aurora.system.domain.SysVisitLog;
import com.aurora.system.mapper.SysVisitLogMapper;
import com.aurora.system.service.SysVisitLogService;
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
 * @Date 2021/10/11
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class SysVisitLogServiceImpl extends ServiceImpl<SysVisitLogMapper, SysVisitLog> implements SysVisitLogService {

    @Autowired
    private SysVisitLogMapper visitLogMapper;

    /**
     * 保存访问日志
     *
     * @param visitLog
     * @return
     */
    @Override
    public boolean saveVisitLog(SysVisitLog visitLog) {
        visitLog.setVisitTime(new Date());
        return save(visitLog);
    }

    /**
     * 新增访问日志
     *
     * @param visitLog 访问日志对象
     */
    @Override
    public void insertVisitLog(SysVisitLog visitLog) {
        saveVisitLog(visitLog);
    }

    /**
     * 查询系统访问日志集合
     *
     * @param visitLog 访问日志对象
     * @return 访问日志集合
     */
    @Override
    public List<SysVisitLog> selectVisitLogList(SysVisitLog visitLog) {
        return list(new QueryWrapper<>(visitLog));
    }

    /**
     * 批量删除系统访问日志
     *
     * @param ids 需要删除的访问日志ID
     * @return 结果
     */
    @Override
    public boolean deleteVisitLogByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids));
    }

    /**
     * 查询访问日志详细
     *
     * @param id 访问ID
     * @return 访问日志对象
     */
    @Override
    public SysVisitLog selectVisitLogById(Long id) {
        return getById(id);
    }

    /**
     * 清空访问日志
     */
    @Override
    public void cleanVisitLog() {
        visitLogMapper.cleanVisitLog();
    }
}
