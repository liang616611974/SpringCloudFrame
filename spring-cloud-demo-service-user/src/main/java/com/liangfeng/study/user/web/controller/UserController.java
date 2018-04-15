package com.liangfeng.study.user.web.controller;


import com.liangfeng.study.bean.dto.request.UserOptRequestBody;
import com.liangfeng.study.bean.dto.response.UserQueryResponseBody;
import com.liangfeng.study.bean.dto.response.UserResponseBody;
import com.liangfeng.study.core.component.id.IdGenerator;
import com.liangfeng.study.core.web.dto.request.Request;
import com.liangfeng.study.core.web.dto.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserController
 * @Description:
 * @date  2017/10/27 14:08
 */
@RestController
public class UserController {

    @Autowired
    IdGenerator idGenerator;

    @ApiOperation(value = "创建用户", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "创建用户请求参数", paramType = "body", dataType = "UserOptRequestBody")
    @PostMapping("/user/create")
    public Response create(@Validated(Request.Add.class) @RequestBody UserOptRequestBody requestBody) {
        //int i = 1 / 0;
        return Response.success();
    }

    @ApiOperation(value = "创建用户", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "修改用户请求参数", paramType = "body", dataType = "UserOptRequestBody")
    @PostMapping("/user/modify")
    public Response modify(@Validated(Request.Modify.class) @RequestBody UserOptRequestBody requestBody) {
        return Response.success();
    }

    @ApiOperation(value = "删除用户", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "删除用户请求参数", paramType = "body", dataType = "UserOptRequestBody")
    @PostMapping("/user/remove")
    public Response remove(@Validated(Request.Remove.class) @RequestBody UserOptRequestBody requestBody) {
        return Response.success();
    }

    @ApiOperation(value = "获取用户", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "获取用户请求参数", paramType = "body", dataType = "UserOptRequestBody")
    @PostMapping("/user/get")
    public Response<UserResponseBody> get(@Validated(Request.Get.class) @RequestBody UserOptRequestBody requestBody) {
        UserResponseBody responseBody = new UserResponseBody();
        responseBody.setId(requestBody.getId());
        if (requestBody.getId() == 111) {
            responseBody.setUsername("卖家用户");
        } else if (requestBody.getId() == 222){
            responseBody.setUsername("买家用户");
        }else{
            responseBody.setUsername("测试用户");
        }
        responseBody.setAge(18);
        responseBody.setSex("男");
        responseBody.setMobile("13118006543");
        responseBody.setEmail("123456@qq.com");
        return Response.success(responseBody);
    }

    @ApiOperation(value = "查询用户列表", notes = "")
    @ApiImplicitParam(name = "requestBody", value = "查询用户列表参数", paramType = "body", dataType = "UserOptRequestBody")
    @PostMapping("/user/query")
    public Response<UserQueryResponseBody> query(@Validated(Request.Query.class) @RequestBody UserOptRequestBody requestBody) {
        UserQueryResponseBody responseBody1 = new UserQueryResponseBody();
        List<UserResponseBody> users = new ArrayList<>();
        UserResponseBody responseBody = new UserResponseBody();
        responseBody.setId(idGenerator.generateId());
        responseBody.setUsername("测试用户");
        responseBody.setAge(18);
        responseBody.setSex("男");
        responseBody.setMobile("13118006543");
        responseBody.setEmail("123456@qq.com");
        users.add(responseBody);
        responseBody1.setUsers(users);
        return Response.success(responseBody1);
    }

}
