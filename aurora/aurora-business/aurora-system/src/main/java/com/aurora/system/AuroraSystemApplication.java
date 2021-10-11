package com.aurora.system;

import com.aurora.common.security.annotation.EnableAuroraResourceServer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/9
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableDubbo(scanBasePackages = {"com.aurora.system.*"})
@EnableAuroraResourceServer
public class AuroraSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraSystemApplication.class, args);
    }

}