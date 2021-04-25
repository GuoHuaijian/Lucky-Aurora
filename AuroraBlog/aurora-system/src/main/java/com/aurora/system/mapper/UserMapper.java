package com.aurora.system.mapper;

import com.aurora.system.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/30
 * @E-mail 564559079@qq.com
 * @Version 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}