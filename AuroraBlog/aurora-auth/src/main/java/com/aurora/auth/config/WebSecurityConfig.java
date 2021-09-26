package com.aurora.auth.config;

import com.aurora.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/26
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder()  {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    /**
//     * 无权限处理类
//     */
//    @Autowired
//    private UserAccessDeniedHandler userAccessDeniedHandler;
//
//    /**
//     * 用户未登录处理类
//     */
//    @Autowired
//    private UserNotLoginHandler userNotLoginHandler;
//
//    /**
//     * 用户登录成功处理类
//     */
//    @Autowired
//    private UserLoginSuccessHandler userLoginSuccessHandler;
//
//    /**
//     * 用户登录失败处理类
//     */
//    @Autowired
//    private UserLoginFailureHandler userLoginFailureHandler;
//
//    /**
//     * 用户登出成功处理类
//     */
//    @Autowired
//    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 允许匿名访问所有接口 主要是 oauth 接口
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**","/login/**").permitAll()
//                .antMatchers(JwtConfig.antMatchers.split(",")).permitAll()
                // 其他的需要登陆后才能访问
                .anyRequest().authenticated()
                .and().httpBasic()
                // 配置未登录处理类
                //.and().httpBasic().authenticationEntryPoint(userNotLoginHandler)
//                // 配置登录URL
//                .and().formLogin().loginProcessingUrl("/login/user")
//                // 配置登录成功处理类
//                .successHandler(userLoginSuccessHandler)
//                // 配置登录失败处理类
//                .failureHandler(userLoginFailureHandler)
//                // 配置登出地址
//                .and().logout().logoutUrl("/logout/submit")
//                // 配置用户登出处理类
//                .logoutSuccessHandler(userLogoutSuccessHandler)
                // 配置没有权限处理类
                //.and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
                // 开启跨域
                .and().cors()
                // 禁用跨站请求伪造防护
                .and().csrf().disable();
        // 禁用session（使用Token认证）
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        http.formLogin().loginProcessingUrl("/login/user");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
