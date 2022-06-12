package com.aurora.system.service.rpc;

import com.aurora.common.core.utils.bean.BeanUtil;
import com.aurora.rpc.system.RemoteUserService;
import com.aurora.rpc.system.domain.User;
import com.aurora.system.domain.SysUser;
import com.aurora.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2022/6/12
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@DubboService(version = "1.0.0", interfaceClass = RemoteUserService.class)
@RequiredArgsConstructor
public class RemoteUserServiceImpl implements RemoteUserService{

    private final SysUserService userService;

    /**
     * 获取用户
     * @param ids
     * @return
     */
    @Override
    public List<User> getUsers(List<Long> ids) {
        List<SysUser> sysUsers = userService.listByIds(ids);
        List<User> users = BeanUtil.copyListProperties(sysUsers, User::new);
        return users;
    }
}
