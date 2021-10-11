package com.aurora.system.mapper;

import com.aurora.system.domain.SysVisitLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/11
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Mapper
public interface SysVisitLogMapper extends BaseMapper<SysVisitLog> {

    /**
     * 清空访问日志
     */
    void cleanVisitLog();
}
