package com.liangfeng.study.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
