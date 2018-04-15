package com.liangfeng.study.core.config;


import com.liangfeng.study.core.component.id.IdGenerator;
import com.liangfeng.study.core.component.id.SnowflakeIdGenerator;
import com.liangfeng.study.core.component.id.UUIDGenerator;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        logger.info("AppConfig系统应用配置:{}",appConfig);
        boolean enable = Boolean.valueOf(appConfig.getIdGeneratorEnable());
        if(!enable){
            return null;
        }
        // 声明变量
        IdGenerator idGenerator;
        String idGeneratorType = DEFAULT_ID_GENERATOR_TYPE;
        if (StringUtils.isNoneBlank(appConfig.getIdGeneratorType()) && SNOWFLAKE_ID_GENERATOR_TYPE.equals(appConfig.getIdGeneratorType().toLowerCase())) {
            idGeneratorType = SNOWFLAKE_ID_GENERATOR_TYPE;
        }
        logger.info("=======================注册 {}IdGenerator 开始=========================",idGeneratorType);
        try {
            // 使用snowflake主键生成策略
            if (StringUtils.isNoneBlank(appConfig.getIdGeneratorType()) && SNOWFLAKE_ID_GENERATOR_TYPE.equals(appConfig.getIdGeneratorType().toLowerCase())) {
                idGenerator = new SnowflakeIdGenerator(Integer.valueOf(appConfig.getSnowflakeWorkerId()), Integer.valueOf(appConfig.getSnowflakeDatacenterId()));
            }else {
                // 否则使用uuid主键生成策略
                idGenerator = new UUIDGenerator();
            }
        } catch (Exception e) {
            logger.error("注册{}IdGenerator 发生异常", idGeneratorType,e);
            throw new RuntimeException("注册" + idGeneratorType + "IdGenerator发生异常", e);
        }
        logger.info("=======================注册 {}IdGenerator 结束=========================",idGeneratorType);
        return idGenerator;
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

    }
}
