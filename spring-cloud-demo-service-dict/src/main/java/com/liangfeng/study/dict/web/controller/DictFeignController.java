package com.liangfeng.study.dict.web.controller;


import com.liangfeng.study.api.dto.request.DictApiQueryRequestbody;
import com.liangfeng.study.api.dto.response.DictApiQueryResponsebody;
import com.liangfeng.study.core.web.dto.response.Response;
import com.liangfeng.study.dict.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictApiController
 * @Description:
 * @date  2018/9/1 0001 下午 6:18
 */
@RestController
@Api(description = "字典模块远程服务接口")
public class DictFeignController {

    @Autowired
    DictService service;

    @ApiOperation(value = "获取字典集合")
    @PostMapping("/dict/api/getDictMap")
    public Response<DictApiQueryResponsebody> getDictMap(@RequestBody DictApiQueryRequestbody requestbody) {
        return service.getDictMap(requestbody);
    }
}
