package com.qf;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by 54110 on 2020/6/3.
 */
@SpringBootApplication
@EnableDiscoveryClient
//标注当前工程为springbootadmin的服务端
@EnableAdminServer
public class DangdangAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangdangAdminApplication.class,args);
    }
}
