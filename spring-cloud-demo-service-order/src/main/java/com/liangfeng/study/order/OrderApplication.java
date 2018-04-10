package com.liangfeng.study.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: OrderApplication
 * @Description:
 * @date  2017/10/31 9:50
 */

@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"com.liangfeng.study"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.liangfeng.study"})
public class OrderApplication {

    public static void main(String[] args) {
            SpringApplication.run(OrderApplication.class, args);
    }

}
