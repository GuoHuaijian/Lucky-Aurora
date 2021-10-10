package com.aurora.system.service;

import com.aurora.system.domain.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
     * @param log
     * @return
     */
    boolean saveLog(SysLog log);

    /**
     * 新增操作日志
     *
     * @param log 操作日志对象
     */
    void insertLog(SysLog log);

    /**
     * 查询系统操作日志集合
     *
     * @param log 操作日志对象
     * @return 操作日志集合
     */
    List<SysLog> selectLogList(SysLog log);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的操作日志ID
     * @return 结果
     */
    boolean deleteLogByIds(Long[] ids);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    SysLog selectLogById(Long id);

    /**
     * 清空操作日志
     */
    void cleanLog();


}
