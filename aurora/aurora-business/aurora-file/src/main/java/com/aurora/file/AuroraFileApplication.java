package com.aurora.file;

import com.aurora.common.security.annotation.EnableAuroraResourceServer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * describe: 文件上传工具类
 *
 * @author Guo Huaijian
 * @date 2021/9/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"}, exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = {"com.aurora.*"})
@EnableAuroraResourceServer
public class AuroraFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraFileApplication.class, args);
    }

}
