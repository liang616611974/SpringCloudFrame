package com.liangfeng.study.dict;


import com.liangfeng.study.core.constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsApplication
 * @Description:
 * @date  2017/10/31 9:50
 */
@ServletComponentScan
@EnableFeignClients(basePackages = {AppConstant.BASE_PACKAGE})
@EnableCircuitBreaker
//@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {AppConstant.BASE_PACKAGE})
public class DictApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictApplication.class, args);
    }

}
