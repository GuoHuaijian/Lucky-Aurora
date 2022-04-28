package com.aurora.common.security.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * describe: 自定义权限实现
 *
 * @author Guo Huaijian
 * @date 2021/1/2
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service("ss")
public class PermissionService {

    private String defaultRolePrefix = "ROLE_";

    /**
     * 验证权限
     *
     * @param authority
     * @return
     */
    public boolean hasAuthority(String authority) {
        return this.hasAnyAuthority(authority);
    }

    /**
     * 验证是否包含其中任意权限
     *
     * @param authorities
     * @return
     */
    public boolean hasAnyAuthority(String... authorities) {
        return this.hasAnyAuthorityName(null, authorities);
    }

    /**
     * 验证角色
     *
     * @param role
     * @return
     */
    public boolean hasRole(String role) {
        return this.hasAnyRole(role);
    }

    /**
     * 验证是否拥有任意角色
     *
     * @param roles
     * @return
     */
    public boolean hasAnyRole(String... roles) {
        return this.hasAnyAuthorityName(this.defaultRolePrefix, roles);
    }

    /**
     * 验证是否包含
     *
     * @param prefix
     * @param roles
     * @return
     */
    private boolean hasAnyAuthorityName(String prefix, String... roles) {
        Set<String> roleSet = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        String[] var = roles;
        for (int i = 0; i < roles.length; ++i) {
            String defaultedRole = getRoleWithDefaultPrefix(prefix, var[i]);
            if (roleSet.contains(defaultedRole)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 角色默认前缀
     *
     * @param defaultRolePrefix
     * @param role
     * @return
     */
    private static String getRoleWithDefaultPrefix(String defaultRolePrefix, String role) {
        if (role == null) {
            return null;
        } else if (StringUtils.hasLength(defaultRolePrefix)) {
            return role.startsWith(defaultRolePrefix) ? role : defaultRolePrefix + role;
        } else {
            return role;
        }
    }

}
