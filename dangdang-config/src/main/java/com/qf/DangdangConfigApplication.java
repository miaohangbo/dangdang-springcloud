package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by 54110 on 2020/6/3.
 */
@SpringBootApplication
//eureka的客户端
@EnableDiscoveryClient
//config的服务端
@EnableConfigServer
public class DangdangConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangdangConfigApplication.class);
    }
}
