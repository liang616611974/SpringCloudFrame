package com.liangfeng.study.common.config;


import com.liangfeng.study.common.helper.UUIDHelper;
import com.liangfeng.study.common.helper.WebHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: AppLoggerMDCFilterConfig
 * @Description: 存放在MDC中的数据，logback可以直接引用并作为日志信息打印出来. 示例：%X{userId}
 * @date  2017/10/30 11:04
 */
@Configuration
public class AppLogMDCFilterConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppLogMDCFilterConfig.class);

    private static final String LOGGER_FILTER_NAME = "LoggerMDCFilter";

    /**
     * 注册系统日志记录过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean loggerMDCFilter() {
        logger.info("=======================注册{} 开始=========================",LOGGER_FILTER_NAME);
        FilterRegistrationBean loggerMDCFilter = null;
        try {
            loggerMDCFilter = new FilterRegistrationBean();
            loggerMDCFilter.addUrlPatterns("/*");
            loggerMDCFilter.setFilter(new LoggerMDCFilter());
        } catch (Exception e) {
            logger.error("注册{}发生异常",LOGGER_FILTER_NAME, e);
            throw new RuntimeException("注册" + LOGGER_FILTER_NAME + "发生异常", e);
        }
        logger.info("=======================注册{} 结束=========================",LOGGER_FILTER_NAME);
        return loggerMDCFilter;
    }


    public class LoggerMDCFilter extends OncePerRequestFilter {

        private static final String USER_ID_KEY_SESSION = "userId";
        private static final String USER_ID_KEY = "userId";
        private static final String REQUEST_URI_KEY = "requestURI";
        private static final String REMOTE_ADDR_KEY = "remoteAddr";
        private static final String UUID_KEY = "uuid";
        private static final String PREFIX = "(";
        private static final String SUFFIX = ")";

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
            try {
                //1.加入当前登陆用户id
                String userId = WebHelper.getSession(request, USER_ID_KEY_SESSION);
                MDC.put(USER_ID_KEY, userId);
                //2.加入请求URI
                String requestURI = request.getRequestURI() + (request.getQueryString() == null ? "" : "?" + request.getQueryString());
                MDC.put(REQUEST_URI_KEY, requestURI);
                //3.加入远程访问地址
                String remoteAddr = WebHelper.getRequestIp(request);
                MDC.put(REMOTE_ADDR_KEY, remoteAddr);
                //4.为每一个请求创建一个ID，方便查找日志时可以根据ID查找出一个http请求所有相关日志
                String uuid = UUIDHelper.generateUUID();
                MDC.put(UUID_KEY, uuid);
                //5.放行
                chain.doFilter(request, response);
            } catch (Exception e) {
                logger.error("存放系统MDC日志属性发生异常", e);
                throw new RuntimeException("存放系统MDC日志属性发生异常", e);
            }
        }
    }

}
