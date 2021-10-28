package com.aurora.system.service;

import com.aurora.rpc.system.domain.SysVisitLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/11
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface SysVisitLogService extends IService<SysVisitLog> {


    /**
     * 保存访问日志
     *
     * @param visitLog
     * @return
     */
    boolean saveVisitLog(SysVisitLog visitLog);

    /**
     * 新增访问日志
     *
     * @param visitLog 访问日志对象
     */
    void insertVisitLog(SysVisitLog visitLog);

    /**
     * 查询系统访问日志集合
     *
     * @param visitLog 访问日志对象
     * @return 访问日志集合
     */
    List<SysVisitLog> selectVisitLogList(SysVisitLog visitLog);

    /**
     * 批量删除系统访问日志
     *
     * @param ids 需要删除的访问日志ID
     * @return 结果
     */
    boolean deleteVisitLogByIds(Long[] ids);

    /**
     * 查询访问日志详细
     *
     * @param id 访问ID
     * @return 访问日志对象
     */
    SysVisitLog selectVisitLogById(Long id);

    /**
     * 清空访问日志
     */
    void cleanVisitLog();

}
