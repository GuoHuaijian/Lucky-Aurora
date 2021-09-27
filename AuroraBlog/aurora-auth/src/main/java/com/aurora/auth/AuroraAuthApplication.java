package com.aurora.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Admin
 */
@EnableResourceServer
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableDubbo(scanBasePackages = {"com.aurora.auth.*"})
public class AuroraAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAuthApplication.class, args);
    }

}
