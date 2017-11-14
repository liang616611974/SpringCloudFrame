package com.liangfeng.study.common.config;

import com.liangfeng.study.common.helper.WebHelper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  * @Title AppShutdownAuthFilterConfig.java
 *  * @Description 
 *  * @version 1.0
 *  * @author Liangfeng
 *  * @date 2017/11/8 0008 下午 10:47
 *  
 */
@Configuration
public class AppShutdownAuthFilterConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppShutdownAuthFilterConfig.class);

    private static final String SHUTDOWN_AUTH_FILTER_NAME = "shutdownAuthFilter";

    @Autowired
    private AppShutdownConfig appShutdownConfig;

    /**
     * 注册系统关闭认证过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean shutdownAuthFilter() {
        logger.info("AppShutdownConfig:{}",appShutdownConfig);
        FilterRegistrationBean shutdownAuthFilter = null;
        boolean b = Boolean.valueOf(appShutdownConfig.enabled);
        if (!b) {
            return null;
        }
        logger.info("=======================注册{} 开始=========================", SHUTDOWN_AUTH_FILTER_NAME);
        try {
            shutdownAuthFilter = new FilterRegistrationBean();
            shutdownAuthFilter.addUrlPatterns("/" + appShutdownConfig.id);
            shutdownAuthFilter.setFilter(new ShutdownAuthFilter());
        } catch (Exception e) {
            logger.error("注册{}发生异常", SHUTDOWN_AUTH_FILTER_NAME, e);
            throw new RuntimeException("注册" + SHUTDOWN_AUTH_FILTER_NAME + "发生异常", e);
        }
        logger.info("=======================注册{} 结束=========================", SHUTDOWN_AUTH_FILTER_NAME);
        return shutdownAuthFilter;
    }


    public class ShutdownAuthFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String ip = WebHelper.getRequestIp(request);
            if (ip.equals("127.0.0.1") || ip.equals("localhost") || ip.equals("0:0:0:0:0:0:0:1")) {
                filterChain.doFilter(request, response);
            } else {
                logger.error("非法关闭应用服务,攻击IP:" + ip);
            }
        }
    }

    @Component
    @ConfigurationProperties(prefix = "endpoints.shutdown", ignoreUnknownFields = true)
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getSensitive() {
            return sensitive;
        }

        public void setSensitive(String sensitive) {
            this.sensitive = sensitive;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }
}
