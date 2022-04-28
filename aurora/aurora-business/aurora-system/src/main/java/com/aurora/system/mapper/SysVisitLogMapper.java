package com.aurora.system.mapper;

import com.aurora.rpc.system.domain.SysVisitLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/11
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Mapper
public interface SysVisitLogMapper extends BaseMapper<SysVisitLog> {

    /**
     * 清空访问日志
     */
    void cleanVisitLog();
}
