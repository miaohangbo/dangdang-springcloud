package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * Created by 54110 on 2020/6/3.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class DangdangZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangdangZipkinApplication.class,args);
    }
}
