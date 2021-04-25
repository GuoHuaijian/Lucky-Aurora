package com.aurora.common.security.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * describe: 自定义权限实现
 *
 * @Author Guo Huaijian
 * @Date 2021/1/2
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
@Service("ss")
public class PermissionService {
    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(permission);
        return true;
    }

}
