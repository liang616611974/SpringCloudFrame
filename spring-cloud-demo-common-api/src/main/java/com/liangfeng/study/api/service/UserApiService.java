package com.liangfeng.study.api.service;


import com.liangfeng.study.api.config.RcFeignConfig;
import com.liangfeng.study.api.dto.request.UserOptRequestBody;
import com.liangfeng.study.api.dto.response.UserResponseBody;
import com.liangfeng.study.api.service.fallback.UserApiServiceFallback;
import com.liangfeng.study.common.pojo.dto.Request;
import com.liangfeng.study.common.pojo.dto.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserApiService
 * @Description:
 * @date  2017/10/31 15:07
 */
@FeignClient(value = "spring-cloud-user", configuration = RcFeignConfig.class, fallback=UserApiServiceFallback.class)
public interface UserApiService {

    @PostMapping( value = "/user/create")
    Response create(UserOptRequestBody requestBody);

    @PostMapping("/user/get")
    public Response<UserResponseBody> get(UserOptRequestBody requestBody);

}
