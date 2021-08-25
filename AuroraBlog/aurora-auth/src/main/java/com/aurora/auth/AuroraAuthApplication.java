package com.aurora.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableDubbo(scanBasePackages = {"com.aurora.auth.service.impl"})
public class AuroraAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAuthApplication.class, args);
    }

}
