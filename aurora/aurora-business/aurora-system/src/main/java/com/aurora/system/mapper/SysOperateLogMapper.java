package com.aurora.system.mapper;

import com.aurora.rpc.system.domain.SysOperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/30
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Mapper
public interface SysOperateLogMapper extends BaseMapper<SysOperateLog> {

    /**
     * 清空操作日志
     */
    void cleanOperateLog();
}
