package com.aurora.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.File;

/**
 * describe: 通用映射配置
 *
 * @Author Guo
 * @Date 2021/9/10
 * @Version 1.0
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

    @Resource
    private FileConfig fileConfig;

    /**
     * 本地文件上传路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 本地文件上传路径
        registry.addResourceHandler(fileConfig.getLocal().getPrefix() + "/**")
                .addResourceLocations("file:" + fileConfig.getLocal().getPath() + File.separator);
    }

    /**
     * 开启跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping(fileConfig.getLocal().getPrefix() + "/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 设置允许的方法
                .allowedMethods("GET");
    }
}
