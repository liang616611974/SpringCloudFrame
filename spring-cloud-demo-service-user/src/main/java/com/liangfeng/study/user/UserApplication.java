package com.liangfeng.study.user;


import com.liangfeng.study.common.constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserApplication
 * @Description:
 * @date  2017/10/26 16:38
 */
@EnableFeignClients(basePackages = {AppConstant.BASE_PACKAGE})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {AppConstant.BASE_PACKAGE})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
