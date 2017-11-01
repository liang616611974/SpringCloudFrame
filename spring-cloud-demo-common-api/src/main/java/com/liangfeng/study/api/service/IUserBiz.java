package com.liangfeng.study.api.service;


import com.liangfeng.study.api.config.RcFeignConfig;
import com.liangfeng.study.api.service.fallback.IUserBizFallback;
import com.liangfeng.study.bean.service.UserApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: IUserBiz
 * @Description:
 * @date  2017/11/1 13:56
 */
@FeignClient(value = "spring-cloud-user", configuration = RcFeignConfig.class, fallback= IUserBizFallback.class)
public interface IUserBiz extends UserApiService{
}
