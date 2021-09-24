package com.aurora.file;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author huaijian.guo
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"}, exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = {"com.aurora.*"})
public class AuroraFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraFileApplication.class, args);
    }

}
