package com.aurora.rpc.system;

import com.aurora.rpc.system.domain.User;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2022/6/12
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface RemoteUserService {

    /**
     * 获取用户
     * @param ids
     * @return
     */
    List<User> getUsers(List<Long> ids);
}
