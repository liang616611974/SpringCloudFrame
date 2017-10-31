package com.liangfeng.study.common.config;


import com.liangfeng.study.common.helper.WebHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Title ApiLogRecordAopConfig.java
 * @Description Api 接口日志打印
 * @version 1.0
 * @author Liangfeng	
 * @date 2017/10/26 17:38
 */
@Component
@Aspect
public class WebApiLogPrintAopConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebApiLogPrintAopConfig.class);

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    // 切入点设置
    @Pointcut("execution(public * com.*.*.dev.controller.*.*(..))")
    public void apiLog() {
    }

    @Before("apiLog()")
    public void before(JoinPoint joinPoint) {
        // 1.设置开始时间
        startTime.set(System.currentTimeMillis());
        // 2.获取相应参数
        HttpServletRequest request = WebHelper.getRequest();
        // 3.打印日志
        logger.info("【 requestUrl:{} , httpMethod:{} , IP:{} , classMethod:{}.{} , args:{} 】",
                request.getRequestURL().toString(),
                request.getMethod(),
                WebHelper.getRequestIp(request),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "result", pointcut = "apiLog()")
    public void doAfterReturning(Object result) throws Throwable {
        // 打印结果和消耗时间
        logger.info("【 result:{} , spendTime: {} milliseconds 】",
                result,
                (System.currentTimeMillis() - startTime.get()));
    }
}


