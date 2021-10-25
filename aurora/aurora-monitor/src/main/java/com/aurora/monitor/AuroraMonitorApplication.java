package com.aurora.monitor;

import com.aurora.common.security.annotation.EnableAuroraResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/28
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"}, exclude = {DataSourceAutoConfiguration.class})
@EnableAuroraResourceServer
public class AuroraMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraMonitorApplication.class, args);
    }

}
