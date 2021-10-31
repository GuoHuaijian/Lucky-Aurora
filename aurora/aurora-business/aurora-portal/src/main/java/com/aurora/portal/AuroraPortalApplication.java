package com.aurora.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guohuaijian
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
public class AuroraPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraPortalApplication.class, args);
    }

}
