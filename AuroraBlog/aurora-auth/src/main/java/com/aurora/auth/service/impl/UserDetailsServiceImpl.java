package com.aurora.auth.service.impl;

import com.aurora.auth.domain.AuthUser;
import com.aurora.auth.service.AuthUserService;
import com.aurora.common.core.utils.AssertUtil;
import com.aurora.common.security.domain.SecurityUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserService authUserService;

    /**
     * 根据用户名查用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        AuthUser authUser = authUserService.getUserByName(username);
        AssertUtil.notNull(authUser, new UsernameNotFoundException("用户名不存在"));
        AssertUtil.notTrue("2".equals(authUser.getStatus()), new LockedException("用户已禁用"));
        if (authUser != null) {
            SecurityUserDetails userDetails = new SecurityUserDetails();
            BeanUtils.copyProperties(authUser, userDetails);
            userDetails.setUsername(authUser.getUsername());
            // 角色和权限集合
            Set<GrantedAuthority> authorities = new HashSet<>();
            Set<String> roleList = new HashSet<>(authUserService.getRolesByUserId(userDetails.getUserId()));
            roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
            Set<String> authList = new HashSet<>(authUserService.getAuthsByUserId(userDetails.getUserId()));
            for (String s : authList) {
                Set<String> list = new HashSet<>(Arrays.asList(s.split(",")));
                list.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth)));
            }
            userDetails.setAuthorities(authorities);
            return userDetails;
        }
        return null;
    }

}
