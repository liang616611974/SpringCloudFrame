package com.liangfeng.study.dict.web.controller;


import com.liangfeng.study.core.exception.ParamException;
import com.liangfeng.study.core.helper.ExcelHelper;
import com.liangfeng.study.core.web.dto.request.GetRequestbody;
import com.liangfeng.study.core.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.core.web.dto.request.Request;
import com.liangfeng.study.core.web.dto.response.AddResponsebody;
import com.liangfeng.study.core.web.dto.response.Response;
import com.liangfeng.study.dict.service.DictService;
import com.liangfeng.study.dict.web.request.DictAddOrMdfRequestbody;
import com.liangfeng.study.dict.web.request.DictQueryRequestbody;
import com.liangfeng.study.dict.web.response.DictGetResponsebody;
import com.liangfeng.study.dict.web.response.DictQueryResponsebody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictController
 * @Description:
 * @date 2018-07-02
 */
@RestController
@Api(description = "字典模块接口")
public class DictController {

    @Autowired
    DictService service;

    @ApiOperation(value = "创建字典")
    @PostMapping("/dict/add")
    public Response<AddResponsebody> add(@Validated(Request.Add.class) @RequestBody DictAddOrMdfRequestbody requestbody) {
        return Response.success(service.add(requestbody));
    }

    @ApiOperation(value = "修改字典")
    @PostMapping("/dict/modify")
    public Response modify(@Validated({Request.Modify.class}) @RequestBody DictAddOrMdfRequestbody requestbody) {
        service.modify(requestbody);
        return Response.success();
    }

    @ApiOperation(value = "删除字典")
    @PostMapping("/dict/remove")
    public Response remove(@Validated @RequestBody RemoveRequestbody requestbody) {
        service.remove(requestbody);
        return Response.success();
    }

    @ApiOperation(value = "获取字典详细信息")
    @PostMapping("/dict/get")
    public Response<DictGetResponsebody> get(@Validated @RequestBody GetRequestbody requestbody) {
        return Response.success(service.get(requestbody));
    }

    @ApiOperation(value = "分页查询字典")
    @PostMapping("/dict/queryPage")
    public Response<DictQueryResponsebody> queryPage(@Validated @RequestBody DictQueryRequestbody requestbody) {
        return Response.success(service.queryPage(requestbody));
    }

    @ApiOperation(value = "导出字典")
    @RequestMapping(value = "/dict/export",method = {RequestMethod.GET,RequestMethod.POST})
    public void export(DictQueryRequestbody requestbody,String downloadName,HttpServletRequest request, HttpServletResponse response){
        DictQueryResponsebody responsebody = service.query(requestbody);
        ExcelHelper.exportForDownloadByObj(request,response,downloadName,responsebody.getRows(),DictGetResponsebody.class);
    }

    @ApiOperation(value = "查询字典作前端缓存")
    @PostMapping("/dict/webCache")
    public Response<Map<String, List<DictGetResponsebody>>> queryForWebCache(@Validated @RequestBody DictQueryRequestbody requestbody){
        return Response.success(service.queryForWebCache(requestbody));
    }
}

