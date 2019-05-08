package com.liangfeng.study.core.config;


import com.google.common.base.Charsets;
import com.liangfeng.study.core.component.id.IdGenerator;
import com.liangfeng.study.core.component.id.SnowflakeIdGenerator;
import com.liangfeng.study.core.component.id.UUIDGenerator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: AppConfig
 * @Description:
 * @date  2017/10/27 11:59
 */

@Configuration
@Slf4j
public class AppCommonConfig {

    //private static final Logger logger = LoggerFactory.getLogger(AppCommonConfig.class);
    private static final String DEFAULT_ID_GENERATOR_TYPE = "uuid"; // 默认主键生成器类型,uuid主键
    private static final String SNOWFLAKE_ID_GENERATOR_TYPE = "snowflake"; // snowflake主键生成器类型
    @Autowired
    AppConfig appConfig;

    /**
     * id主键生成器
     *
     * @return
     */
    @Bean
    public IdGenerator idGenerator() {
        log.info("AppConfig系统应用配置:{}", appConfig);
        boolean enable = Boolean.valueOf(appConfig.getIdGeneratorEnable());
        if (!enable) {
            return null;
        }
        // 声明变量
        IdGenerator idGenerator;
        String idGeneratorType = DEFAULT_ID_GENERATOR_TYPE;
        if (StringUtils.isNoneBlank(appConfig.getIdGeneratorType()) && SNOWFLAKE_ID_GENERATOR_TYPE.equals(appConfig.getIdGeneratorType().toLowerCase())) {
            idGeneratorType = SNOWFLAKE_ID_GENERATOR_TYPE;
        }
        log.info("=======================注册 {}IdGenerator 开始=========================", idGeneratorType);
        try {
            // 使用snowflake主键生成策略
            if (StringUtils.isNoneBlank(appConfig.getIdGeneratorType()) && SNOWFLAKE_ID_GENERATOR_TYPE.equals(appConfig.getIdGeneratorType().toLowerCase())) {
                idGenerator = new SnowflakeIdGenerator(Integer.valueOf(appConfig.getSnowflakeWorkerId()), Integer.valueOf(appConfig.getSnowflakeDatacenterId()));
            } else {
                // 否则使用uuid主键生成策略
                idGenerator = new UUIDGenerator();
            }
        } catch (Exception e) {
            log.error("注册{}IdGenerator 发生异常", idGeneratorType, e);
            throw new RuntimeException("注册" + idGeneratorType + "IdGenerator发生异常", e);
        }
        log.info("=======================注册 {}IdGenerator 结束=========================", idGeneratorType);
        return idGenerator;
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        log.info("=======================设置App临时上传目录 开始=========================");
        File uploadTemp = new File(appConfig.uploadTemp);
        if (!uploadTemp.exists()) {
            uploadTemp.mkdirs();
        }
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(appConfig.uploadTemp);
        MultipartConfigElement element = factory.createMultipartConfig();
        log.info("App临时上传目录:{}", appConfig.uploadTemp);
        log.info("=======================设置App临时上传目录 结束=========================");
        return element;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {

        //使用HttpClient替换默认实现,可以支持gzip
        HttpClient httpClient = HttpClientBuilder.create().build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        //解决中文乱码
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charsets.UTF_8));

        return restTemplate;
    }

    /**
     * 给SpringTask提供一个多线程的TaskScheduler，
     * 因为SpringTask默认是单线程的，如果task1和task2执行的相隔时间太短，而task1执行时间太长，会导致等待task1执行完后，再执行task2
     */
    @Configuration
    @EnableScheduling
    public class SchedulerConfig {
        @Bean
        public TaskScheduler taskScheduler() {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            //线程池大小
            scheduler.setPoolSize(10);
            //线程名字前缀
            scheduler.setThreadNamePrefix("spring-task-thread");
            return scheduler;
        }
    }

    @Component
    @Data
    public class AppConfig {

        /**
         * 是否启用idGenerator
         */
        @Value("${app.idGenerator.enabled}")
        private String idGeneratorEnable;

        /**
         * ID主键生成器类型
         */
        @Value("${app.idGenerator.type}")
        private String idGeneratorType;

        /**
         * SnowflakeIdGenerator.workerId 工作ID (0-31)
         */
        @Value("${app.snowflake.workerId}")
        private String snowflakeWorkerId;

        /**
         * SnowflakeIdGenerator.datacenterId 数据中心ID (0-31)
         */
        @Value("${app.snowflake.datacenterId}")
        private String snowflakeDatacenterId;

        /**
         * 是否打印接口的异常堆栈信息
         */
        @Value("${app.api.printExceptionStackTrace}")
        private String printExceptionStackTrace;

        /**
         * App 临时上传目录，防止tomcat自创建的临时目录被系统清除。
         */
        @Value("${app.upload.temp}")
        private String uploadTemp;
    }
}
