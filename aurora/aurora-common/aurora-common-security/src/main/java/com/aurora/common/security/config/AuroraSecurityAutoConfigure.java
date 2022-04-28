package com.aurora.common.security.config;

import com.aurora.common.security.component.AuroraTokenEnhancer;
import com.aurora.common.security.component.AuroraUserAuthenticationConverter;
import com.aurora.common.security.security.UserPermissionEvaluator;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/29
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Configuration
@EnableConfigurationProperties(IgnoreUrlProperties.class)
public class AuroraSecurityAutoConfigure {

    /**
     * 用户权限注解
     */
    @Resource
    public UserPermissionEvaluator userPermissionEvaluator;

    @Bean
    public AuroraTokenEnhancer auroraTokenEnhancer() {
        return new AuroraTokenEnhancer();
    }

    @Bean
    public AuroraUserAuthenticationConverter userAuthenticationConverter() {
        return new AuroraUserAuthenticationConverter();
    }

    private final ResourceServerProperties resource;

    public AuroraSecurityAutoConfigure(ResourceServerProperties resource) {
        this.resource = resource;
    }

    @Bean
    public RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl(this.resource.getTokenInfoUri());
        services.setClientId(this.resource.getClientId());
        services.setClientSecret(this.resource.getServiceId());
        return services;
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
}
