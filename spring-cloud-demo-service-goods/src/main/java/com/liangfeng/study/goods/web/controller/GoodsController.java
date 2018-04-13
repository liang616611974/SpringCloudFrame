package com.liangfeng.study.goods.web.controller;


import com.liangfeng.study.common.web.dto.request.GetRequestbody;
import com.liangfeng.study.common.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.common.web.dto.request.Request;
import com.liangfeng.study.common.web.dto.response.Response;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import com.liangfeng.study.goods.web.response.GoodsGetResponsebody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: OrderController
 * @Description:
 * @date  2017/10/31 16:34
 */
@RestController
@Api(description = "商品模块接口")
public class GoodsController {

   /* @Autowired
    IUserBiz userBiz;*/

    @Autowired
    GoodsService service;

    @ApiOperation(value = "创建商品", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "请求参数", paramType = "body", dataType = "GoodsAddOrMdfRequestbody")
    @PostMapping("/goods/add")
    public Response add(@Validated(Request.Add.class) @RequestBody GoodsAddOrMdfRequestbody requestBody) {
        service.add(requestBody);
        return Response.success();
    }

    @ApiOperation(value = "修改商品", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "请求参数", paramType = "body", dataType = "GoodsAddOrMdfRequestbody")
    @PostMapping("/goods/modify")
    public Response modify(@Validated({Request.Modify.class}) @RequestBody GoodsAddOrMdfRequestbody requestBody) {
        service.modify(requestBody);
        return Response.success();
    }

    @ApiOperation(value = "删除商品", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "请求参数", paramType = "body", dataType = "RemoveRequestbody")
    @PostMapping("/goods/remove")
    public Response Remove(@Validated({Request.Remove.class}) @RequestBody RemoveRequestbody requestBody) {
        service.remove(requestBody);
        return Response.success();
    }

    @ApiOperation(value = "获取商品详细信息", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "请求参数", paramType = "body", dataType = "GetRequestbody")
    @PostMapping("/goods/get")
    public Response<GoodsGetResponsebody> get(@Validated(Request.Get.class) @RequestBody GetRequestbody requestBody) {
        return Response.success(service.get(requestBody));
    }

    @ApiOperation(value = "分页查询商品列表", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "请求参数", paramType = "body", dataType = "GetRequestbody")
    @PostMapping("/goods/queryPage")
    public Response<GoodsGetResponsebody> queryPage(@Validated(Request.Get.class) @RequestBody GetRequestbody requestBody) {
        return Response.success(service.get(requestBody));
    }


}
