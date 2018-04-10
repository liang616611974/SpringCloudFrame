package com.liangfeng.study.goods.web.controller;


import com.liangfeng.study.api.service.IUserBiz;
import com.liangfeng.study.bean.dto.request.GoodsOptRequestBody;
import com.liangfeng.study.bean.dto.request.UserOptRequestBody;
import com.liangfeng.study.bean.dto.response.GoodsResponseBody;
import com.liangfeng.study.bean.dto.response.UserResponseBody;
import com.liangfeng.study.common.component.id.IdGenerator;
import com.liangfeng.study.common.web.Request;
import com.liangfeng.study.common.web.Response;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsSaveOrUptRequestBody;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: OrderController
 * @Description:
 * @date  2017/10/31 16:34
 */
@RestController
public class GoodsController {

    @Autowired
    IUserBiz userBiz;

    @Autowired
    GoodsService service;

    @ApiOperation(value = "创建商品", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "创建商品请求参数", paramType = "body", dataType = "GoodsOptRequestBody")
    @PostMapping("/goods/create")
    public Response add(@Validated(Request.add.class) @RequestBody GoodsSaveOrUptRequestBody requestBody) {
        service.add(requestBody);
        return Response.success();
    }

    @ApiOperation(value = "获取商品", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "获取商品请求参数", paramType = "body", dataType = "GoodsOptRequestBody")
    @PostMapping("/goods/get")
    public Response<GoodsResponseBody> get(@Validated(Request.Get.class) @RequestBody GoodsOptRequestBody requestBody) {
        // 1.定义变量
        GoodsResponseBody responseBody = null;
        UserOptRequestBody userOptRequestBody = null;
        Response<UserResponseBody> response = null;
        // 2.获取卖家数据
        userOptRequestBody = new UserOptRequestBody();
        userOptRequestBody.setId(111L);
        response = userBiz.get(userOptRequestBody);
        UserResponseBody sellUser = response.getResponseBody();
       /* // 3.获取买家数据
        userOptRequestBody.setId(222L);
        response = userBiz.get(userOptRequestBody);
        UserResponseBody buyUser = response.getResponseBody();*/
        //4.返回结果
        responseBody = new GoodsResponseBody();
        responseBody.setId(idGenerator.generateId());
        responseBody.setSellUserId(sellUser.getId());
        responseBody.setSellUsername(sellUser.getUsername());
        responseBody.setType(1);
        responseBody.setGoodsName("耐克运动鞋");
        responseBody.setPrice(new BigDecimal(279));
        return Response.success(responseBody);
    }


}
