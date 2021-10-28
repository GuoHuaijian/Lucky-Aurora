package com.aurora.system.mapper;

import com.aurora.rpc.system.domain.SysLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    int cleanLoginLog();
}
