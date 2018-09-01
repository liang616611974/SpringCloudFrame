package com.liangfeng.study.api.service.fallback;


import com.liangfeng.study.api.dto.request.DictApiQueryRequestbody;
import com.liangfeng.study.api.dto.response.DictApiQueryResponsebody;
import com.liangfeng.study.api.service.DictFeignService;
import com.liangfeng.study.core.web.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictApiServiceFallback
 * @Description:
 * @date  2018/9/1 0001 下午 5:18
 */
@Component
@Slf4j
public class DictFeignServiceFallback implements DictFeignService {

    @Override
    public Response<DictApiQueryResponsebody> getDictMap(DictApiQueryRequestbody requestbody) {
        log.info("调用获取字典集合接口失败");
        DictApiQueryResponsebody responsebody = new DictApiQueryResponsebody();
        responsebody.setDictMap(new HashMap<>());
        return Response.serverErr("获取字典集合失败");
    }
}
