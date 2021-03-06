package com.aurora.system;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableDubbo(scanBasePackages = {"com.aurora.system.*"})
public class AuroraSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraSystemApplication.class, args);
    }

}
