package com.qf;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by 54110 on 2020/6/2.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
//开启分布式事务
@EnableDistributedTransaction
public class DangdangPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangdangPayApplication.class,args);
    }
}
