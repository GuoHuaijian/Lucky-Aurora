package com.aurora.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
public class AuroraAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAdminApplication.class, args);
    }

}
