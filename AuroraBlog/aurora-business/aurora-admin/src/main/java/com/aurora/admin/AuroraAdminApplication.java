package com.aurora.admin;

import com.aurora.common.rocketmq.annotation.EnableAuroraMq;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableDubbo(scanBasePackages = {"com.aurora.*"})
@EnableAuroraMq
public class AuroraAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAdminApplication.class, args);
    }

}
