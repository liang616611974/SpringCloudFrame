package com.liangfeng.study.user.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: AppConfig
 * @Description:
 * @date  2017/10/27 11:59
 */
@Component
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = true)
public class AppConfig {

    /**
     * 是否打印接口的异常堆栈信息
     */
    private String printApiExceptionStackTrace;

    //private String print-api-log-pointcuntExp;

    public String getPrintApiExceptionStackTrace() {
        return printApiExceptionStackTrace;
    }

    public void setPrintApiExceptionStackTrace(String printApiExceptionStackTrace) {
        this.printApiExceptionStackTrace = printApiExceptionStackTrace;
    }
}
