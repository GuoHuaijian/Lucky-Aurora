package com.aurora.auth.service;

import com.aurora.common.core.utils.AssertUtil;
import com.aurora.common.security.domain.SecurityUser;
import com.aurora.rpc.system.RemoteAuthUserService;
import com.aurora.rpc.system.domain.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
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
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference(version = "1.0.0")
    private RemoteAuthUserService remoteAuthUserService;

    /**
     * 根据用户名查用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        AuthUser authUser = remoteAuthUserService.getUserByName(username);
        AssertUtil.notNull(authUser, new UsernameNotFoundException("用户名不存在"));
        AssertUtil.notTrue("2".equals(authUser.getStatus()), new LockedException("用户已禁用"));
        if (authUser != null) {
            SecurityUser securityUser = new SecurityUser();
            BeanUtils.copyProperties(authUser, securityUser);
            securityUser.setUsername(authUser.getUserName());
            // 角色和权限集合
            Set<GrantedAuthority> authorities = new HashSet<>();
            Set<String> roleList = new HashSet<>(remoteAuthUserService.getRolesByUserId(securityUser.getUserId()));
            roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
            Set<String> authList = new HashSet<>(remoteAuthUserService.getAuthsByUserId(securityUser.getUserId()));
            for (String s : authList) {
                Set<String> list = new HashSet<>(Arrays.asList(s.split(",")));
                list.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth)));
            }
            securityUser.setAuthorities(authorities);
            return securityUser;
        }
        return null;
    }

}
