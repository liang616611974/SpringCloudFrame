package com.liangfeng.study.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Title WebMVCConfig.java
 * @Description SpringMVC 配置
 * @version 1.0
 * @author Liangfeng
 * @date 2017/5/30 0030 上午 10:35
 */
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

  /* *//**
     *  使用 fastJsonHttpMessageConvert
     * @param converters
     *//*
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        
        // 1.需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 2.添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);

        // 3.在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 4.添加到转换器集合
        HttpMessageConverter<?> converter = fastConverter;
        converters.add(converter);
    }
    */

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链

        // addPathPatterns 用于添加拦截规则

        // excludePathPatterns 用户排除拦截

        //registry.addInterceptor(new DemoInterceptor()).addPathPatterns("/**");

       // registry.addInterceptor(new DemoInterceptor2()).addPathPatterns("/**");

        super.addInterceptors(registry);

    }

    /**
     * 注册Cors跨域防御
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 限制了路径和域名的访问
        //registry.addMapping("/api/**").allowedOrigins("http://localhost:8080");
        //super.addCorsMappings(registry);
    }

}
