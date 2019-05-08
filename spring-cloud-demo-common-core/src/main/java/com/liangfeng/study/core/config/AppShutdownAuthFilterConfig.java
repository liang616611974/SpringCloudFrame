package com.liangfeng.study.core.config;

import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.helper.WebHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  * @Title AppShutdownAuthFilterConfig.java
 *  * @Description 
 *  * @version 1.0
 *  * @author Liangfeng
 *  * @date 2017/11/8 0008 下午 10:47
 *  
 */
@Configuration
@Slf4j
public class AppShutdownAuthFilterConfig {

    //private static final Logger log = LoggerFactory.getLogger(AppShutdownAuthFilterConfig.class);

    private static final String SHUTDOWN_AUTH_FILTER_NAME = "ShutdownAuthFilter";

    @Autowired
    private AppShutdownConfig appShutdownConfig;

    /**
     * 注册系统关闭认证过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean shutdownAuthFilter() {
        log.info("AppShutdownConfig系统关闭配置:{}", appShutdownConfig);
        FilterRegistrationBean shutdownAuthFilter = null;
        boolean b = Boolean.valueOf(appShutdownConfig.getEnabled());
        // 未开启应用关闭服务
        if (!b) {
            shutdownAuthFilter = new FilterRegistrationBean();
            shutdownAuthFilter.addUrlPatterns("/shutdown");
            shutdownAuthFilter.setFilter(new OncePerRequestFilter() {
                @Override
                protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                    String ip = WebHelper.getRequestIp(request);
                    if (ip.equals("127.0.0.1") || ip.equals("localhost") || ip.equals("0:0:0:0:0:0:0:1")) {
                        response.reset();
                        response.setHeader("Content-type", "text/html;charset=" + AppConstant.ENCODING);
                        request.setCharacterEncoding(AppConstant.ENCODING);
                        PrintWriter writer = response.getWriter();
                        writer.print("应用关闭服务未开启....");
                        writer.flush();
                        writer.close();
                    } else {
                        log.error("应用关闭服务没开启!!!攻击IP:" + ip);
                    }
                }
            });
            return shutdownAuthFilter;
        }
        // 开启应用关闭服务
        log.info("=======================注册 {} 开始=========================", SHUTDOWN_AUTH_FILTER_NAME);
        try {
            shutdownAuthFilter = new FilterRegistrationBean();
            shutdownAuthFilter.addUrlPatterns("/" + appShutdownConfig.getId());
            shutdownAuthFilter.setFilter(new ShutdownAuthFilter());
        } catch (Exception e) {
            log.error("注册{}发生异常", SHUTDOWN_AUTH_FILTER_NAME, e);
            throw new RuntimeException("注册" + SHUTDOWN_AUTH_FILTER_NAME + "发生异常", e);
        }
        log.info("=======================注册 {} 结束=========================", SHUTDOWN_AUTH_FILTER_NAME);
        return shutdownAuthFilter;
    }


    public class ShutdownAuthFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String ip = WebHelper.getRequestIp(request);
            if (ip.equals("127.0.0.1") || ip.equals("localhost") || ip.equals("0:0:0:0:0:0:0:1")) {
                filterChain.doFilter(request, response);
            } else {
                log.error("非法关闭应用服务,攻击IP:" + ip);
            }
        }
    }

    @Component
    @ConfigurationProperties(prefix = "endpoints.shutdown", ignoreUnknownFields = true)
    @Data
    public class AppShutdownConfig {

        /**
         * 系统关闭URL标识，shutdownId:shutdown,则反问URL"/shutdown" 即可关闭应用服务器
         */
        //@Value("${endpoints.shutdown.id}")
        private String id;

        /**
         * 是否开启系统关闭
         */
        //@Value("${endpoints.shutdown.enabled}")
        private String enabled;

        /**
         * 是否禁用密码验证
         */
        private String sensitive;

    }
}
