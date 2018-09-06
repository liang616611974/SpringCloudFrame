package com.liangfeng.study.zuul;

import com.liangfeng.study.zuul.web.filter.PreRequestLogFilter;
import com.liangfeng.study.zuul.web.provider.DictFallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @Title ZuulApplication.java
 * @Description 
 * @version 1.0
 * @author Liangfeng
 * @date 2017/11/14 0014 下午 11:31
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }

    @Bean
    public DictFallbackProvider dictFallbackProvider() {
        return new DictFallbackProvider();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
