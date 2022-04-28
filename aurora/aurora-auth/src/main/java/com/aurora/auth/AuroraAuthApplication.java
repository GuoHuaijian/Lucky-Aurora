package com.aurora.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/1/1
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableDubbo(scanBasePackages = {"com.aurora.auth.*"})
public class AuroraAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAuthApplication.class, args);
    }

}
