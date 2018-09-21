package com.liangfeng.study.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: ConfigServerApplication
 * @Description:
 * @date  2018/9/13 15:55
 */


@EnableDiscoveryClient // 配置中心支持
@EnableConfigServer // 配置中心支持
@EnableEurekaClient
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
