package com.liangfeng.study.goods.web.controller;


import com.liangfeng.study.core.helper.ExcelHelper;
import com.liangfeng.study.core.web.dto.request.GetRequestbody;
import com.liangfeng.study.core.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.core.web.dto.request.Request;
import com.liangfeng.study.core.web.dto.response.AddResponsebody;
import com.liangfeng.study.core.web.dto.response.Response;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import com.liangfeng.study.goods.web.request.GoodsQueryRequestbody;
import com.liangfeng.study.goods.web.response.GoodsGetResponsebody;
import com.liangfeng.study.goods.web.response.GoodsQueryResponsebody;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsController
 * @Description:
 * @date 2018-07-26
 */
@RestController
@Api(description = "商品模块接口")
public class GoodsController {

    @Autowired
    GoodsService service;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "创建商品")
    @PostMapping("/goods/add")
    public Response<AddResponsebody> add(@Validated(Request.Add.class) @RequestBody GoodsAddOrMdfRequestbody requestbody) {
        return Response.success(service.add(requestbody));
    }

    @ApiOperation(value = "修改商品")
    @PostMapping("/goods/modify")
    public Response modify(@Validated({Request.Modify.class}) @RequestBody GoodsAddOrMdfRequestbody requestbody) {
        service.modify(requestbody);
        return Response.success();
    }

    @ApiOperation(value = "删除商品")
    @PostMapping("/goods/remove")
    public Response remove(@Validated @RequestBody RemoveRequestbody requestbody) {
        service.remove(requestbody);
        return Response.success();
    }

    @ApiOperation(value = "获取商品详细信息")
    @PostMapping("/goods/get")
    public Response<GoodsGetResponsebody> get(@Validated @RequestBody GetRequestbody requestbody) {
        return Response.success(service.get(requestbody));
    }

    @ApiOperation(value = "分页查询商品")
    @PostMapping("/goods/queryPage")
    public Response<GoodsQueryResponsebody> queryPage(@Validated @RequestBody GoodsQueryRequestbody requestbody) {
        return Response.success(service.queryPage(requestbody));
    }

    @ApiOperation(value = "导出商品")
    @RequestMapping(value = "/goods/export",method = {RequestMethod.GET,RequestMethod.POST})
    public void export(GoodsQueryRequestbody requestbody,String downloadName,HttpServletRequest request, HttpServletResponse response){
        GoodsQueryResponsebody responsebody = service.query(requestbody);
        ExcelHelper.exportForDownloadByObj(request,response,downloadName,responsebody.getRows(),GoodsGetResponsebody.class);
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/user/id")
    public Object findById(String id) {
        //int i=1/0;
        String result = restTemplate.getForObject("http://springcloud-dict/dict/test111",String.class);
        return result;
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/user/id2")
    public Object findById2(String id) {
        //int i=1/0;
        String result = restTemplate.getForObject("http://springcloud-dict/dict/test",String.class);
        return result;
    }

    public String findByIdFallback(String id) {
        return "测试HystrixCommand";
    }
   
}

