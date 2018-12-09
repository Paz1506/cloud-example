package com.zaytsevp.autoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AutoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoServiceApplication.class, args);
    }
}
