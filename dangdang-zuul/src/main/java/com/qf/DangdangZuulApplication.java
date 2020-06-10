package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by 54110 on 2020/6/3.
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class DangdangZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(DangdangZuulApplication.class,args);
    }
}
