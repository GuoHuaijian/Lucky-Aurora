package com.aurora.admin;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableDubbo(scanBasePackages = {"com.aurora.*"})
public class AuroraAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAdminApplication.class, args);
    }

}
