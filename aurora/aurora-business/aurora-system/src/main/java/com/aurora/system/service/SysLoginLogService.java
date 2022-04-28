package com.aurora.system.service;

import com.aurora.system.domain.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface SysLoginLogService extends IService<SysLoginLog> {

    /**
     * 新增系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    void insertLoginLog(SysLoginLog loginLog);

    /**
     * 查询系统登录日志集合
     *
     * @param loginLog 访问日志对象
     * @return 登录记录集合
     */
    List<SysLoginLog> selectLoginLogList(SysLoginLog loginLog);

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的登录日志ID
     * @return
     */
    boolean deleteLoginLogByIds(Long[] ids);

    /**
     * 清空系统登录日志
     */
    void cleanLoginLog();

}
