package com.aurora.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/29
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aurora.file")
public class FileConfig {

    private Local local;

    private AliOssConfig aliOss;

    private MinioConfig minio;

    private QiNiuConfig qiNiu;

    private FastDfs fastDfs;

    /**
     * 本地存储配置
     */
    @Data
    public static class Local {

        private String path;

        private String prefix;

        private String domain;
    }

    /**
     * 阿里云配置
     */
    @Data
    public static class AliOssConfig {

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

    }

    /**
     * Minio配置
     */
    @Data
    public static class MinioConfig {

        /**
         * 服务地址
         */
        private String url;

        /**
         * 用户名
         */
        private String accessKey;

        /**
         * 密码
         */
        private String secretKey;

        /**
         * 存储桶名称
         */
        private String bucketName;

    }

    /**
     * 七牛云配置
     */
    @Data
    public static class QiNiuConfig {

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

    /**
     * FastDfs配置
     */
    @Data
    public static class FastDfs {

        private String domain;
    }
}
