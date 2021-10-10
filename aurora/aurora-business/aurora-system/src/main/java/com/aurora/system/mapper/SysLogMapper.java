package com.aurora.system.mapper;

import com.aurora.system.domain.SysLog;
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
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 清空操作日志
     */
    void cleanLog();
}
