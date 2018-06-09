package com.liangfeng.study.core.config;


import com.liangfeng.study.core.component.id.IdGenerator;
import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.helper.SpringContextHelper;
import com.liangfeng.study.core.helper.WebHelper;
import com.liangfeng.study.core.web.dto.request.AddOrMdfRequestbody;
import com.liangfeng.study.core.web.dto.request.BaseRequestbody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Title ApiLogRecordAopConfig.java
 * @Description Api 接口日志打印
 * @version 1.0
 * @author Liangfeng	
 * @date 2017/10/26 17:38
 */
@Component
@Aspect
public class WebRequestSetAndLogAopConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebRequestSetAndLogAopConfig.class);

    private static final ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    // 切入点设置
    @Pointcut(AppConstant.POINTCUT_CONTROLLER)
    public void logRequest() {
    }

    @Around("logRequest()")
    public Object aroud(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1.设置开始时间
        startTime.set(System.currentTimeMillis());
        // 2.获取相应对象
        HttpServletRequest request = WebHelper.getRequest();
        // 3.设置请求体
        setRequestbody(joinPoint, request);
        // 4.请求前打印日志
        printBefore(joinPoint, request);
        // 5.调用
        Object result = joinPoint.proceed(joinPoint.getArgs());
        // 6.请求后打印日志
        printAfter(result);
        // 7.返回结果
        return result;
    }

    /**
     * 打印请求前日志
     * @param joinPoint
     * @param request
     */
    private void printBefore(JoinPoint joinPoint, HttpServletRequest request) {
        logger.info("【 requestUrl:{} , httpMethod:{} , IP:{} , classMethod:{}.{} , args:{} 】",
                request.getRequestURL().toString(),
                request.getMethod(),
                WebHelper.getRequestIp(request),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 打印请求后日志
     * @param result
     */
    private void printAfter(Object result) {
        // 打印结果和消耗时间
        logger.debug("【 result:{} , spendTime: {} milliseconds 】",
                result,
                (System.currentTimeMillis() - startTime.get()));
    }

    /**
     * 设置请求体
     * @param joinPoint
     * @param request
     */
    private void setRequestbody(JoinPoint joinPoint, HttpServletRequest request) {
        // 1.定义参数
        IdGenerator idGenerator = SpringContextHelper.getBean(IdGenerator.class);
        Date now = new Date();
        Long userId = WebHelper.getSession(request, AppConstant.SESSION_ATTR_NAME_USERID);
        List<Long> userRoles = WebHelper.getSession(request,AppConstant.SESSION_ATTR_NAME_USERROLES);
        // 2.设置请求体属性
        for(Object arg : joinPoint.getArgs()){
            // 设置BaseRequestbody属性
            if(arg instanceof BaseRequestbody){
                BaseRequestbody requestbody = (BaseRequestbody)arg;
                requestbody.setUserId(userId);
                requestbody.setUserRoleIds(userRoles);
            }
            // 设置AddOrMdfRequestbody属性
            if(arg instanceof AddOrMdfRequestbody){
                AddOrMdfRequestbody requestbody = (AddOrMdfRequestbody) arg;
                // 如果是新增的方法
                if(joinPoint.getSignature().getName().startsWith(AppConstant.ADD_METHOD_PREFIX)){
                    requestbody.setId(idGenerator.generateId());
                    requestbody.setCreUser(userId);
                    requestbody.setCreTime(now);
                }
                requestbody.setMdfUser(userId);
                requestbody.setMdfTime(now);
            }
        }
    }
}


