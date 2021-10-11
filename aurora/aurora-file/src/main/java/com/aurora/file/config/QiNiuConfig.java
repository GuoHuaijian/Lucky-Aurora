package com.aurora.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/10 14:27
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "qiniuyun")
public class QiNiuConfig {

    /**
     * 需要操作的账号的AK
     */
    private String accessKey;
    /**
     * 需要操作的账号的SK
     */
    private String secretKey;
    /**
     * 要上传的空间名称
     */
    private String bucket;
    /**
     * 图片访问路径前缀
     */
    private String path;

    private Long imageMaxSize;

    private Long imageMaxNameLength;
}
