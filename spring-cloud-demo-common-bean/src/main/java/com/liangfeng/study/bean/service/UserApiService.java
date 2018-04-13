package com.liangfeng.study.bean.service;


import com.liangfeng.study.bean.dto.request.UserOptRequestBody;
import com.liangfeng.study.bean.dto.response.UserResponseBody;
import com.liangfeng.study.common.web.dto.response.Response;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserApiService
 * @Description:
 * @date  2017/10/31 15:07
 */

public interface UserApiService {

    @PostMapping( value = "/user/create")
    Response create(UserOptRequestBody requestBody);

    @PostMapping("/user/get")
    public Response<UserResponseBody> get(UserOptRequestBody requestBody);

}
