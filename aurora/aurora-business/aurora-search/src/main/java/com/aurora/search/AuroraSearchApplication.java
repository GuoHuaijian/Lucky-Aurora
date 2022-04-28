package com.aurora.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/9
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"}, exclude = {DataSourceAutoConfiguration.class})
public class AuroraSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraSearchApplication.class, args);
    }

}
