package com.aurora.common.config.config.dubbo;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.aurora.common.config.config.dubbo.fallback.ConsumerFallback;
import com.aurora.common.config.config.dubbo.fallback.ProviderFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/28
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Slf4j
@Configuration
public class DubboFallbackConfig {

    public DubboFallbackConfig() {
        DubboAdapterGlobalConfig.setProviderFallback(new ProviderFallback());
        DubboAdapterGlobalConfig.setConsumerFallback(new ConsumerFallback());
    }
}
