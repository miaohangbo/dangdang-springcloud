package com.qf;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by 54110 on 2020/6/2.
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
//@EnableHystrix
//开启分布式事务
@EnableDistributedTransaction
public class DangdangUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangdangUserApplication.class,args);
    }

    /*@Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }*/
}
