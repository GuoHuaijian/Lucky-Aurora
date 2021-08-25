package com.aurora.common.security.config;

import com.aurora.common.security.filter.JwtAuthenticationFilter;
import com.aurora.common.security.handler.*;
import com.aurora.common.security.security.UserPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * describe: 系统安全核心配置 开启方法权限注解
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 无权限处理类
     */
    @Autowired
    private UserAccessDeniedHandler userAccessDeniedHandler;

    /**
     * 用户未登录处理类
     */
    @Autowired
    private UserNotLoginHandler userNotLoginHandler;

    /**
     * 用户登录成功处理类
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    /**
     * 用户登录失败处理类
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    /**
     * 用户登出成功处理类
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    /**
     * 用户权限注解
     */
    @Autowired
    private UserPermissionEvaluator userPermissionEvaluator;

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义PermissionEvaluator
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }

    /**
     * 安全权限配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 权限配置
        http.authorizeRequests()
                // 获取白名单（不进行权限验证）
                .antMatchers(JwtConfig.antMatchers.split(",")).permitAll()
                // 其他的需要登陆后才能访问
                .anyRequest().authenticated()
                // 配置未登录处理类
                .and().httpBasic().authenticationEntryPoint(userNotLoginHandler)
                // 配置登录URL
                .and().formLogin().loginProcessingUrl("/login/user")
                // 配置登录成功处理类
                .successHandler(userLoginSuccessHandler)
                // 配置登录失败处理类
                .failureHandler(userLoginFailureHandler)
//                // 配置登出地址
//                .and().logout().logoutUrl("/logout/submit")
//                // 配置用户登出处理类
//                .logoutSuccessHandler(userLogoutSuccessHandler)
                // 配置没有权限处理类
                .and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
                // 开启跨域
                .and().cors()
                // 禁用跨站请求伪造防护
                .and().csrf().disable();
        // 禁用session（使用Token认证）
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT过滤器
        http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

}
