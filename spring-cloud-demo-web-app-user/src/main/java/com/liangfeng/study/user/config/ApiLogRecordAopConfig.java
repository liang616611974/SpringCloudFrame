package com.liangfeng.study.user.config;


import com.liangfeng.study.eureka.common.constant.SystemConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
public class ApiLogRecordAopConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApiLogRecordAopConfig.class);

    // 切入点表达式
    private static final String pointcuntExp = "execution(public * com.banggood.fastdfsstore.admin.dev.controller.*.*(..))";

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut(pointcuntExp)
    public void log() {
    }


    @Before("log()")
    public void before(JoinPoint joinPoint) {
        // 1.设置开始时间
        startTime.set(System.currentTimeMillis());
        // 2.获取相应参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 3.打印日志
        logger.info("【 requestUrl:{} , httpMethod:{} , IP:{} , classMethod:{}.{} , args:{} 】",
                request.getRequestURL().toString(),
                request.getMethod(),
                request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturning(Object result) throws Throwable {
        // 打印结果和消耗时间
        logger.info("【 result:{} {} spendTime: {} milliseconds 】",
                result,
                SystemConstant.SYS_LINE_SEPARATOR,
                (System.currentTimeMillis() - startTime.get()));
    }
}


