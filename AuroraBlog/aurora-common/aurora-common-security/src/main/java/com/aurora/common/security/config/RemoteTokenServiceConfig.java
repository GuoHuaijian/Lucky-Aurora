package com.aurora.common.security.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/29
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Configuration
public class RemoteTokenServiceConfig {

    private final ResourceServerProperties resource;

    public RemoteTokenServiceConfig(ResourceServerProperties resource) {
        this.resource = resource;
    }

    @Bean
    public RemoteTokenServices remoteTokenServices(){
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl(this.resource.getTokenInfoUri());
        services.setClientId(this.resource.getClientId());
        services.setClientSecret(this.resource.getServiceId());
        return services;
    }
}
