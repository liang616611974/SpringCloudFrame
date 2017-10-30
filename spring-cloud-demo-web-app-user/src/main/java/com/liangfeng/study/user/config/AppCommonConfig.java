package com.liangfeng.study.user.config;


import com.liangfeng.study.common.component.id.IdGenerator;
import com.liangfeng.study.common.component.id.SnowflakeIdGenerator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: AppConfig
 * @Description:
 * @date  2017/10/27 11:59
 */

@Configuration
public class AppCommonConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppCommonConfig.class);

    private static final String ID_GENERATOR_NAME = "SnowflakeIdGenerator";

    @Autowired
    AppConfig appConfig;

    /**
     * id主键生成器
     *
     * @return
     */
    @Bean
    public IdGenerator idGenerator() {
        logger.info("=======================注册{} 开始=========================",ID_GENERATOR_NAME);
        logger.info("AppConfig系统应用配置:{}",appConfig);
        // 使用SnowflakeId主键策略
        IdGenerator idGenerator;
        try {
            idGenerator = new SnowflakeIdGenerator(Integer.valueOf(appConfig.getWorkerId()), Integer.valueOf(appConfig.getDatacenterId()));
        } catch (Exception e) {
            logger.error("注册{} 发生异常", ID_GENERATOR_NAME,e);
            throw new RuntimeException("注册" + ID_GENERATOR_NAME + "发生异常", e);
        }
        logger.info("=======================注册{} 结束=========================",ID_GENERATOR_NAME);
        return idGenerator;
    }


    @Component
    @ConfigurationProperties(prefix = "app", ignoreUnknownFields = true)
    public class AppConfig {

        /**
         * SnowflakeIdGenerator.workerId 工作ID (0-31)
         */
        private String workerId;

        /**
         * SnowflakeIdGenerator.datacenterId 数据中心ID (0-31)
         */
        private String datacenterId;

        /**
         * 是否打印接口的异常堆栈信息
         */
        private String printApiExceptionStackTrace;

        //private String print-api-log-pointcuntExp;

        public String getWorkerId() {
            return workerId;
        }

        public void setWorkerId(String workerId) {
            this.workerId = workerId;
        }

        public String getDatacenterId() {
            return datacenterId;
        }

        public void setDatacenterId(String datacenterId) {
            this.datacenterId = datacenterId;
        }

        public String getPrintApiExceptionStackTrace() {
            return printApiExceptionStackTrace;
        }

        public void setPrintApiExceptionStackTrace(String printApiExceptionStackTrace) {
            this.printApiExceptionStackTrace = printApiExceptionStackTrace;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }
}
