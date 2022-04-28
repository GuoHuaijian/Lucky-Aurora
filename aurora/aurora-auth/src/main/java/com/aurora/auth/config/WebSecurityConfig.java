package com.aurora.auth.config;

import com.aurora.common.security.config.IgnoreUrlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * describe: 安全访问控制
 *
 * @author Guo Huaijian
 * @date 2021/9/26
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Order(99)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    public IgnoreUrlProperties urlProperties;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 允许匿名访问所有接口 主要是 oauth 接口
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
                .and().httpBasic()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                // 允许访问的链接
                .and().authorizeRequests().antMatchers(urlProperties.getUrlStr()).permitAll()
                // 其余所有接口需要认证
                .anyRequest().authenticated()
                // 开启跨域
                .and().cors()
                // 禁用跨站请求伪造防护
                .and().csrf().disable();
    }
}
