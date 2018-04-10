package com.liangfeng.study.common.config;

import com.liangfeng.study.common.web.Response;
import com.liangfeng.study.common.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Title WebRestExceptionHandlerConfig.java
 * @Description 系统异常处理，针对不同一样返回不同Rest结果
 * @version 1.0
 * @author Liangfeng
 * @date 2017/10/27 17:34
 */
@RestControllerAdvice
public class WebRestExceptionHandlerConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebRestExceptionHandlerConfig.class);

    @Autowired
    private AppCommonConfig.AppConfig appConfig;

    /**
     * 统一ParamException异常处理
     *
     * @param exception exception
     * @return
     */
    @ExceptionHandler({ParamException.class})
    @ResponseStatus(HttpStatus.OK)
    public Object processException(ParamException exception) {
        logger.error("系统自定义异常处理-ParamException", exception);
        return Response.paramErr(exception.getMessage());
    }

    /**
     * 统一RuntimeException异常处理
     *
     * @param exception exception
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public Object processException(RuntimeException exception) {
        logger.error("系统自定义异常处理-RuntimeException", exception);
        return getErrResponse(exception);
    }

    /**
     * 统一Exception异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public Object processException(Exception exception) {
        logger.error("系统自定义异常处理-exception", exception);
        return getErrResponse(exception);
    }

    /**
     * 获取系统的错误响应
     *
     * @param throwable
     * @return
     */
    private Response getErrResponse(Throwable throwable) {
        // 是否打印异常的堆栈信息
        boolean isPrintStackTrace = Boolean.valueOf(appConfig.getPrintExceptionStackTrace());
        if (isPrintStackTrace) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            String errorMsg = sw.toString();
            return Response.serverErr(errorMsg);
        }
        return Response.serverErr(throwable.getMessage());
    }
}
