package com.aurora.common.security.config;

import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

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
@EnableConfigurationProperties(IgnoreUrlProperties.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
   private IgnoreUrlProperties ignoreUrlProperties;

    /**
     * 放行设置
     *
     * @param httpSecurity
     */
   @Override
   @SneakyThrows
   public void configure(HttpSecurity httpSecurity) {
       httpSecurity.headers().frameOptions().disable();
       ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
               .authorizeRequests();
       ignoreUrlProperties.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
       registry.anyRequest().authenticated().and().csrf().disable();
   }
}
