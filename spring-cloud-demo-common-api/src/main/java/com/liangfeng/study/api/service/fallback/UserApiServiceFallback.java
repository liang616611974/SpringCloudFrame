package com.liangfeng.study.api.service.fallback;


import com.liangfeng.study.api.dto.request.UserOptRequestBody;
import com.liangfeng.study.api.dto.response.UserResponseBody;
import com.liangfeng.study.api.service.UserApiService;
import com.liangfeng.study.common.pojo.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserApiServiceFallback
 * @Description:
 * @date  2017/10/31 16:29
 */
@Component
public class UserApiServiceFallback implements UserApiService {

    private static final Logger logger = LoggerFactory.getLogger(UserApiServiceFallback.class);

    @Override
    public Response create(UserOptRequestBody requestBody) {
        logger.info("调用创建用户接口异常");
        return null;
    }

    @Override
    public Response<UserResponseBody> get(UserOptRequestBody requestBody) {
        logger.info("调用获取用户接口异常");
        return null;
    }
}
