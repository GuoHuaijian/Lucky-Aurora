package com.aurora.auth.service.impl;

import com.aurora.auth.domain.SysUser;
import com.aurora.auth.service.SysUserService;
import com.aurora.common.core.utils.AssertUtil;
import com.aurora.common.security.domain.SecurityUser;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * describe: 用户登录Service
 *
 * @author Guo Huaijian
 * @date 2021/1/1
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService userService;

    /**
     * 根据用户名查用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser authUser = userService.getUserByName(username);
        AssertUtil.notNull(authUser, new UsernameNotFoundException("用户名不存在"));
        AssertUtil.notTrue("2".equals(authUser.getStatus()), new LockedException("用户已禁用"));
        SecurityUser securityUser = new SecurityUser();
        BeanUtils.copyProperties(authUser, securityUser);
        securityUser.setUsername(authUser.getUserName());
        // 角色和权限集合
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        Set<String> roleList = userService.getRolesByUserId(securityUser.getUserId());
        roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        Set<String> authList = userService.getAuthsByUserId(securityUser.getUserId());
        for (String authStr : authList) {
            Set<String> set = new HashSet<>(Arrays.asList(authStr.split(",")));
            set.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth)));
        }
        securityUser.setAuthorities(authorities);
        return securityUser;
    }

}
