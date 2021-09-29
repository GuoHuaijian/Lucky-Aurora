package com.aurora.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author huaijian.guo
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"}, exclude = {DataSourceAutoConfiguration.class})
public class AuroraSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraSearchApplication.class, args);
    }

}
