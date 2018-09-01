package com.liangfeng.study.dict.web.controller;


import com.liangfeng.study.core.dto.ImageBO;
import com.liangfeng.study.core.web.dto.response.Response;
import com.liangfeng.study.dict.service.CommonService;
import com.liangfeng.study.dict.web.response.ImgUploadResponsebody;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: CommonController
 * @Description:
 * @date  2018/7/28 0028 下午 2:13
 */
@Api(description = "公共模块接口")
@RestController
@Slf4j
public class CommonController {

    private static final String ALLOW_IMG_TYPE = "png,jpg";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传图片")
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "hasParticiple",value = "是否需要分词",dataType = "Boolean",paramType = "query"),
            @ApiImplicitParam(name = "maxAlternatives",value = "多候选词个数，1-10整数",paramType = "query",allowableValues = "1,2,3,4,5,6,7,8,9,10")
    })*/
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dowloadName",value = "下载名称",dataType = "String",paramType = "query"),
    })
    @PostMapping(value = "/dict/common/uploadImg")
    public Response<ImgUploadResponsebody> multiFileUpload(@ApiParam(value = "图片",required = true) @RequestParam(value = "img", required = true) MultipartFile img, String dowloadName) throws Exception {
        // 1.验证图片
        if (img == null) {
            return Response.paramErr("图片不能为空");
        }
        if (img.getSize() > (1 * 1024 * 1024)) {
            return Response.paramErr("单张图片大小不能超过 1MB");
        }
        String extName = FilenameUtils.getExtension(img.getOriginalFilename());
        if (StringUtils.containsNone(ALLOW_IMG_TYPE, extName)) {
            return Response.paramErr("图片格式错误");
        }

        // 2.返回结果
        ImageBO imageBO = new ImageBO();
        imageBO.setSuffix(extName);
        imageBO.setSize(img.getSize());
        imageBO.setBytes(img.getBytes());
        imageBO.setInputStream(img.getInputStream());
        imageBO.setOriginalFilename(img.getOriginalFilename());
        ImgUploadResponsebody respBody = commonService.uploadImg(imageBO);
        return Response.success(respBody);
    }

    @GetMapping("/dict/instance")
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("spring-cloud-dict");
    }

    @GetMapping("/dict/instance-rest")
    public List<ServiceInstance> showInfoByRestTemplate() throws Exception{
        ResponseEntity<List> result = restTemplate.getForEntity("http://spring-cloud-dict/dict/instance",List.class);
        return result.getBody();
    }

    @GetMapping("/dict/log-instance")
    public Response logDictInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("spring-cloud-dict");
        // 打印当前选择哪个节点
        log.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
        return Response.success();
    }
}
