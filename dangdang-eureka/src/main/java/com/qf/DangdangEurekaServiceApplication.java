package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by 54110 on 2020/6/2.
 */
@SpringBootApplication
@EnableEurekaServer
public class DangdangEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangdangEurekaServiceApplication.class,args);
    }
}
