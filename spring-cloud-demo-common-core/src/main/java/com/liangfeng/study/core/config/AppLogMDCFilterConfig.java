package com.liangfeng.study.core.config;


import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.helper.UUIDHelper;
import com.liangfeng.study.core.helper.WebHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

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
@Slf4j
public class AppLogMDCFilterConfig {

    //private static final Logger logger = LoggerFactory.getLogger(AppLogMDCFilterConfig.class);

    /** 日志记录过滤器名称 */
    private static final String LOGGER_FILTER_NAME = "LoggerMDCFilter";

    /**
     * 注册系统日志记录过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean loggerMDCFilter() {
        log.info("=======================注册 {} 开始=========================",LOGGER_FILTER_NAME);
        FilterRegistrationBean loggerMDCFilter = null;
        try {
            loggerMDCFilter = new FilterRegistrationBean();
            loggerMDCFilter.addUrlPatterns("/*");
            loggerMDCFilter.setFilter(new LoggerMDCFilter());
        } catch (Exception e) {
            log.error("注册{}发生异常",LOGGER_FILTER_NAME, e);
            throw new RuntimeException("注册" + LOGGER_FILTER_NAME + "发生异常", e);
        }
        log.info("=======================注册 {} 结束=========================",LOGGER_FILTER_NAME);
        return loggerMDCFilter;
    }


    public class LoggerMDCFilter extends OncePerRequestFilter {

        /** 日志打印名称UserId */
        private static final String LOG_PRINT_NAME_USERID = "userId";
        /** 日志打印名称requestURI */
        private static final String LOG_PRINT_NAME_REQUESTURI = "requestURI";
        /** 日志打印名称remoteAddr */
        private static final String LOG_PRINT_NAME_REMOTEADDR = "remoteAddr";
        /** 日志打印名称uuid */
        private static final String LOG_PRINT_NAME_UUID = "uuid";
        /*private static final String PREFIX = "(";
        private static final String SUFFIX = ")";*/

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
            try {
                //1.加入当前登陆用户id
                String userId = String.valueOf(WebUtils.getSessionAttribute(request, AppConstant.SESSION_ATTR_NAME_USERID));
                MDC.put(LOG_PRINT_NAME_USERID, userId);
                //2.加入请求URI
                String requestURI = request.getRequestURI() + (request.getQueryString() == null ? "" : "?" + request.getQueryString());
                MDC.put(LOG_PRINT_NAME_REQUESTURI, requestURI);
                //3.加入远程访问地址
                String remoteAddr = WebHelper.getRequestIp(request);
                MDC.put(LOG_PRINT_NAME_REMOTEADDR, remoteAddr);
                //4.为每一个请求创建一个ID，方便查找日志时可以根据ID查找出一个http请求所有相关日志
                String uuid = UUIDHelper.generateUUID();
                MDC.put(LOG_PRINT_NAME_UUID, uuid);
                //5.放行
                chain.doFilter(request, response);
            } catch (Exception e) {
                log.error("存放系统MDC日志属性发生异常", e);
                throw new RuntimeException("存放系统MDC日志属性发生异常", e);
            }
        }
    }

}
