package com.aurora.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/28
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"}, exclude = {DataSourceAutoConfiguration.class})
public class AuroraGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraGatewayApplication.class, args);
    }

}
