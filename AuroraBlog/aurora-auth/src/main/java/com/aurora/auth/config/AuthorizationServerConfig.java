package com.aurora.auth.config;

import com.aurora.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/26
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;


    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        // 令牌保存到redis
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        // 基于JDBC实现，需要事先在数据库配置客户端信息
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }

    /**
     * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // 刷新token需要验证用户
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
                // 密码模式需要
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

    }

    /**
     * 配置客户端详情信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
                    clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
     * 配置令牌端点(Token Endpoint)安全约束
     *
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // // 允许表单认证
        security.allowFormAuthenticationForClients();
        // 允许客户端访问 /oauth/check_token 检查 token
        security.checkTokenAccess("permitAll()");
        security.tokenKeyAccess("permitAll()");
    }

}
