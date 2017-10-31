package com.liangfeng.study.dev.controller;


import com.liangfeng.study.api.dto.request.OrderOptRequestBody;
import com.liangfeng.study.api.dto.request.UserOptRequestBody;
import com.liangfeng.study.api.dto.response.OrderResponseBody;
import com.liangfeng.study.api.dto.response.UserResponseBody;
import com.liangfeng.study.api.service.UserApiService;
import com.liangfeng.study.common.component.id.IdGenerator;
import com.liangfeng.study.common.pojo.dto.Request;
import com.liangfeng.study.common.pojo.dto.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: OrderController
 * @Description:
 * @date  2017/10/31 16:34
 */
@RestController
public class OrderController {

    @Autowired
    IdGenerator idGenerator;

    UserApiService userApiService;

    @ApiOperation(value = "创建订单", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "创建订单请求参数", paramType = "body", dataType = "OrderOptRequestBody")
    @PostMapping("/order/create")
    public Response create(@Validated(Request.Create.class) @RequestBody OrderOptRequestBody requestBody) {
        return Response.success();
    }

    @ApiOperation(value = "获取订单", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "获取订单请求参数", paramType = "body", dataType = "OrderOptRequestBody")
    @PostMapping("/order/get")
    public Response<OrderResponseBody> get(@Validated(Request.Get.class) @RequestBody OrderOptRequestBody requestBody) {
        // 1.定义变量
        OrderResponseBody responseBody = null;
        UserOptRequestBody userOptRequestBody = null;
        Response<UserResponseBody> response = null;
        // 2.获取卖家数据
        userOptRequestBody = new UserOptRequestBody();
        userOptRequestBody.setId(111L);
        response = userApiService.get(userOptRequestBody);
        UserResponseBody sellUser = response.getResponseBody();
        // 3.获取买家数据
        userOptRequestBody.setId(222L);
        response = userApiService.get(userOptRequestBody);
        UserResponseBody buyUser = response.getResponseBody();
        //4.返回结果
        responseBody.setId(idGenerator.generateId());
        responseBody.setOrderNo("123456");
        responseBody.setOrderType(1);
        responseBody.setOrderStatus(1);
        responseBody.setSellUserId(sellUser.getId());
        responseBody.setSellUsername(sellUser.getUsername());
        responseBody.setBuyUserId(buyUser.getId());
        responseBody.setBuyUsername(buyUser.getUsername());
        responseBody.setGoodsName("耐克运动鞋");
        responseBody.setPrice(new BigDecimal(279));
        return Response.success(responseBody);
    }


}
