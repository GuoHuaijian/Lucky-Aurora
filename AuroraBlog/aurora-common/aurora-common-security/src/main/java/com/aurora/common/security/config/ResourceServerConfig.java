package com.aurora.common.security.config;

import com.aurora.common.security.component.AuroraUserAuthenticationConverter;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

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
    private RemoteTokenServices remoteTokenServices;

    @Resource
   private IgnoreUrlProperties ignoreUrlProperties;

    public RemoteTokenServices tokenServices(){
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        tokenConverter.setUserTokenConverter(new AuroraUserAuthenticationConverter());
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
       ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
               .authorizeRequests();
       ignoreUrlProperties.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
       registry.anyRequest().authenticated().and().csrf().disable();
   }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 设置用户信息转化
       resources.tokenServices(tokenServices());
    }
}
