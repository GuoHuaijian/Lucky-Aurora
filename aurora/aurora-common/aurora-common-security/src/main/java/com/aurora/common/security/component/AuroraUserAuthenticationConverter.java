package com.aurora.common.security.component;

import com.aurora.common.security.domain.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * describe: 转换用户信息
 *
 * @Author Guo Huaijian
 * @Date 2021/9/28
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public class AuroraUserAuthenticationConverter implements UserAuthenticationConverter {

    private Collection<? extends GrantedAuthority> defaultAuthorities;
    private UserDetailsService userDetailsService;

    public AuroraUserAuthenticationConverter() {
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setDefaultAuthorities(String[] defaultAuthorities) {
        this.defaultAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaDelimitedString(defaultAuthorities));
    }

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap();
        response.put(USERNAME, authentication.getName());
        Object principal = authentication.getPrincipal();
        if(principal instanceof SecurityUser){
            SecurityUser user;
            user = (SecurityUser) principal;
            if(!StringUtils.isEmpty(user)){
                response.put("userId",user.getUserId());
                response.put("password",user.getPassword());
            }
        }
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(USERNAME, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }

        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            SecurityUser securityUser = new SecurityUser();
            securityUser.setUserId(Long.valueOf(String.valueOf(map.get("userId"))));
            securityUser.setUsername((String) map.get(USERNAME));
            securityUser.setPassword((String) map.get("password"));
            Collection<? extends GrantedAuthority> authorities = this.getAuthorities(map);
            securityUser.setAuthorities((Collection<GrantedAuthority>) authorities);
            if (this.userDetailsService != null) {
                UserDetails user = this.userDetailsService.loadUserByUsername((String)map.get(USERNAME));
                authorities = user.getAuthorities();
                securityUser = (SecurityUser) user;
            }

            return new UsernamePasswordAuthenticationToken(securityUser, "N/A", authorities);
        } else {
            return null;
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        if (!map.containsKey(AUTHORITIES)) {
            return this.defaultAuthorities;
        } else {
            Object authorities = map.get(AUTHORITIES);
            if (authorities instanceof String) {
                return AuthorityUtils.commaSeparatedStringToAuthorityList((String)authorities);
            } else if (authorities instanceof Collection) {
                return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection)authorities));
            } else {
                throw new IllegalArgumentException("Authorities must be either a String or a Collection");
            }
        }
    }
}
