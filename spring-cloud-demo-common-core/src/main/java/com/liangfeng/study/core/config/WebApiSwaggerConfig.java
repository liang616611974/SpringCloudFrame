package com.liangfeng.study.core.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class WebApiSwaggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebApiSwaggerConfig.class);

    private static final String SWAGGER_NAME = "SwaggerApi";

    @Autowired
    SwaggerApiConfig swaggerApiConfig;


    @Bean
    public Docket swaggerApi() {
        Docket docket = null;
        logger.info("SwaggerApiConfig信息配置:{}",swaggerApiConfig);
        // 是否启用swagger配置
        boolean b = Boolean.valueOf(swaggerApiConfig.getEnabled());
        if(!b) {
            docket = new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(new ApiInfoBuilder().build())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("none"))
                    .paths(PathSelectors.any())
                    .build();
            return docket;
        }
        logger.info("=======================注册 {} 开始=========================",SWAGGER_NAME);
        try{
            docket = new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(swaggerApiConfig.getBasePackage()))
                    .paths(PathSelectors.any())
                    .build();
        }catch (Exception e){
            logger.error("注册{}发生异常",SWAGGER_NAME,e);
            throw new RuntimeException("注册" + SWAGGER_NAME + "发生异常", e);
        }
        logger.info("=======================注册 {} 结束=========================",SWAGGER_NAME);
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerApiConfig.getTitle())
                .description("")
                .termsOfServiceUrl("")
                .contact(new Contact(swaggerApiConfig.getContactName(),swaggerApiConfig.getContactUrl(),swaggerApiConfig.getContactEmail()))
                .version(swaggerApiConfig.getVersion())
                .build();
    }

    @Component
    @ConfigurationProperties(prefix = "swagger.api.info", ignoreUnknownFields = false)
    @Data
    public class SwaggerApiConfig{

        /**
         * 是否启用swagger
         */
        //@Value("${swagger.api.info.enabled}")
        private String enabled;
        /**
         * 扫描的包路径
         */
        private String basePackage;
        /**
         * 标题
         */
        private String title;
        /**
         * 版本
         */
        private String version;
        /**
         * 联系人名称
         */
        private String contactName;
        /**
         * 联系网址
         */
        private String contactUrl;
        /**
         * 联系电子邮箱
         */
        private String contactEmail;

    }

}
