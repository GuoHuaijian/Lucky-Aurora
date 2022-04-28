package com.aurora.system.service;

import com.aurora.rpc.system.domain.SysOperateLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/7
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface SysOperateLogService extends IService<SysOperateLog> {

    /**
     * 保存操作日志
     *
     * @param operateLog
     * @return
     */
    boolean saveOperateLog(SysOperateLog operateLog);

    /**
     * 新增操作日志
     *
     * @param operateLog 操作日志对象
     */
    void insertOperateLog(SysOperateLog operateLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    List<SysOperateLog> selectOperateLogList(SysOperateLog operateLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的操作日志ID
     * @return 结果
     */
    boolean deleteOperateLogByIds(Long[] ids);

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    SysOperateLog selectOperateLogById(Long id);

    /**
     * 清空操作日志
     */
    void cleanOperateLog();


}
