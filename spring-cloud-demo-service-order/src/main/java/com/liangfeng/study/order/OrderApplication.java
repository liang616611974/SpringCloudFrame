package com.liangfeng.study.order;


import com.liangfeng.study.core.constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
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
@EnableFeignClients(basePackages = {AppConstant.BASE_PACKAGE})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {AppConstant.BASE_PACKAGE},exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class OrderApplication {

    public static void main(String[] args) {
            SpringApplication.run(OrderApplication.class, args);
    }

}
