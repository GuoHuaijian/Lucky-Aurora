package com.aurora.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Admin
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"}, exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = {"com.aurora.auth.*"})
public class AuroraAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAuthApplication.class, args);
    }

}
