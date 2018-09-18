package com.liangfeng.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: TurbineApplication
 * @Description:
 * @date  2017/11/1 17:40
 */
//@EnableTurbine
//@EnableHystrixDashboard
@EnableTurbineStream
@EnableEurekaClient
@SpringBootApplication
public class TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
