package com.liangfeng.study.user.dev.controller;


import com.liangfeng.study.user.dev.pojo.dto.request.UserOptRequestBody;
import com.liangfeng.study.user.dev.pojo.dto.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserController
 * @Description:
 * @date  2017/10/27 14:08
 */
@RestController
public class UserController {

    @ApiOperation(value = "创建用户", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "创建用户请求参数", paramType = "body", dataType = "UserOptRequestBody")
    @PostMapping("/user/create")
    public Response create(@Validated @RequestBody UserOptRequestBody requestBody){
        return Response.success();
    }

}
