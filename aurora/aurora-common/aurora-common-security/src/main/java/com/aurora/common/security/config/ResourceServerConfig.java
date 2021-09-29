package com.aurora.common.security.config;

import com.aurora.common.security.component.AuroraUserAuthenticationConverter;
import com.aurora.common.security.filter.AuthenticationFilter;
import com.aurora.common.security.handler.UserAccessDeniedHandler;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

/**
 * describe: 系统安全核心配置 开启方法权限注解
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private RemoteTokenServices remoteTokenServices;

    @Resource
    private UserAccessDeniedHandler accessDeniedHandler;

    @Resource
    private AuroraUserAuthenticationConverter userAuthenticationConverter;

    @Resource
    private IgnoreUrlProperties ignoreUrlProperties;

    public RemoteTokenServices tokenServices(){
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        tokenConverter.setUserTokenConverter(userAuthenticationConverter);
        remoteTokenServices.setAccessTokenConverter(tokenConverter);
        return remoteTokenServices;
    }

    /**
     * 放行设置
     *
     * @param httpSecurity
     */
   @Override
   @SneakyThrows
   public void configure(HttpSecurity httpSecurity) {
       httpSecurity.headers().frameOptions().disable();
       // 白名单过滤
       httpSecurity
               .authorizeRequests()
               .antMatchers(ignoreUrlProperties.getUrlStr()).permitAll()
               .anyRequest().authenticated().and().csrf().disable();
       // 配置没有权限处理类
       httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
       // 添加过滤器
       httpSecurity.addFilterBefore(new AuthenticationFilter(),  BasicAuthenticationFilter.class);
   }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 设置用户信息转化
       resources.tokenServices(tokenServices());
    }
}
