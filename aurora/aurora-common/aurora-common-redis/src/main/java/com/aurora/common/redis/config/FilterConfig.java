package com.aurora.common.redis.config;

import com.aurora.common.redis.filter.RepeatableFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * describe: Filter配置
 *
 * @Author Guo Huaijian
 * @Date 2021/10/25
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Configuration
public class FilterConfig {

    /**
     * 为防止重复提交处理构建可重复读取inputStream的request
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*");
        registration.setName("repeatableFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
}
