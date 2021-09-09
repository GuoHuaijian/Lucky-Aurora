package com.aurora.search.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * describe:  ElasticsearchRestTemplate 配置
 *
 * @Author Guo
 * @Date 2021/9/8 11:06
 * @Version 1.0
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.hostname}")
    private String hostname;

    @Value("${elasticsearch.port}")
    private int port;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        RestHighLevelClient highLevelClient = new RestHighLevelClient(restClientBuilder());
        return highLevelClient;
    }

    public RestClientBuilder restClientBuilder() {
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost(hostname, port, "http")
        ).setRequestConfigCallback(
                // 自定义超时时间
                requestConfigBuilder -> requestConfigBuilder
                        // 自定义连接超时时间
                        .setConnectTimeout(5000 * 1000)
                        // 自定义Socket超时时间（默认 30,000 milliseconds ）
                        .setSocketTimeout(6000 * 1000)
        );
        // ES开启安全认证需要设置
        //Authentication(restClientBuilder);
        return restClientBuilder;
    }

    /**
     * 设置ES用户和密码 认证用户
     *
     * @param restClientBuilder
     */
    private void Authentication(RestClientBuilder restClientBuilder) {
        // 设置密码
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("userName", "password"));
        restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultIOReactorConfig(
                        IOReactorConfig.custom()
                                .setIoThreadCount(4)
                                .build()
                ));
    }
}
