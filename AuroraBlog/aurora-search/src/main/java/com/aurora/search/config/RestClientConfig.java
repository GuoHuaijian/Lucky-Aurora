package com.aurora.search.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
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

    @Value("elasticsearch.ip")
    private String hostAndPort;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
