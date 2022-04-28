package com.aurora.auth.mapper;

import com.aurora.auth.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/11/2
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    Set<String> getRolesByUserId(Long userId);

    /**
     * 根据用户id查询用户权限
     *
     * @param userId
     * @return
     */
    Set<String> getAuthsByUserId(Long userId);

    /**
     * 获取全部角色
     *
     * @return
     */
    Set<String> getAllRoles();

    /**
     * 获取全部权限
     *
     * @return
     */
    Set<String> getAllAuths();
}
