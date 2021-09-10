package com.aurora.file.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * describe: AliOSS 配置信息
 *
 * @Author Guo
 * @Date 2021/9/10 14:56
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "alioss")
public class AliOssConfig {

    /**
     * yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
     */
    private String endpoint;
    /**
     * 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
     */
    private String accessKeyId;

    private String accessKeySecret;

    /**
     * 填写Bucket名称，例如examplebucket。
     */
    private String bucketName;

    /**
     * 填写文件名。文件名包含路径，不包含Bucket名称。例如exampledir/exampleobject.txt。
     */
    private String objectName;

    @Bean
    public OSS getOSSClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
