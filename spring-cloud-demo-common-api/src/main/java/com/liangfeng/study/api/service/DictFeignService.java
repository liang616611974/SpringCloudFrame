package com.liangfeng.study.api.service;

import com.liangfeng.study.api.config.RcFeignConfig;
import com.liangfeng.study.api.dto.request.DictApiQueryRequestbody;
import com.liangfeng.study.api.dto.response.DictApiQueryResponsebody;
import com.liangfeng.study.api.service.fallback.DictFeignServiceFallback;
import com.liangfeng.study.core.web.dto.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictApiService
 * @Description:
 * @date  2018/9/1 0001 下午 4:14
 */
@FeignClient(value = "spring-cloud-dict", configuration = RcFeignConfig.class, fallback = DictFeignServiceFallback.class)
public interface DictFeignService {

    /**
     * 获取字典集合
     *
     * @param requestbody
     * @return
     */
    @PostMapping("/dict/api/getDictMap")
    Response<DictApiQueryResponsebody> getDictMap(@RequestBody DictApiQueryRequestbody requestbody);


}
