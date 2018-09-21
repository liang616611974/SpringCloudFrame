package com.liangfeng.study.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserApplication
 * @Description:
 * @date  2017/10/26 16:38
 */
/*@EnableFeignClients(basePackages = {AppConstant.BASE_PACKAGE})
@EnableEurekaClient*/
//@SpringBootApplication(scanBasePackages = {AppConstant.BASE_PACKAGE})

//@EnableDiscoveryClient // 配置中心支持
@EnableEurekaClient
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
