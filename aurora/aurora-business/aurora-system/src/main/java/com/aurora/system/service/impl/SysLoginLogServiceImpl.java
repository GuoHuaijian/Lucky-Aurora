package com.aurora.system.service.impl;

import com.aurora.system.domain.SysLoginLog;
import com.aurora.system.mapper.SysLoginLogMapper;
import com.aurora.system.service.SysLoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper loginLogMapper;

    /**
     * 新增系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    @Override
    public void insertLoginLog(SysLoginLog loginLog) {
        save(loginLog);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param loginLog 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginLog> selectLoginLogList(SysLoginLog loginLog) {
        return list(new QueryWrapper<>(loginLog));
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的登录日志ID
     * @return
     */
    @Override
    public boolean deleteLoginLogByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginLog() {
        loginLogMapper.cleanLoginLog();
    }
}
