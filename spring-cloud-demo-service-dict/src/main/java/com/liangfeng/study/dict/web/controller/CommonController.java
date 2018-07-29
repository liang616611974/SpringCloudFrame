package com.liangfeng.study.dict.web.controller;


import com.liangfeng.study.core.dto.ImageBO;
import com.liangfeng.study.core.web.dto.response.Response;
import com.liangfeng.study.dict.service.CommonService;
import com.liangfeng.study.dict.web.response.ImgUploadResponsebody;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: CommonController
 * @Description:
 * @date  2018/7/28 0028 下午 2:13
 */
@RestController
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    private static final String ALLOW_IMG_TYPE = "png,jpg";

    @Autowired
    CommonService commonService;

    @PostMapping(value = "/dict/common/uploadImg")
    public Response<ImgUploadResponsebody> multiFileUpload(@RequestParam(value = "img", required = false) MultipartFile img,String dowloadName) throws Exception {
        // 1.验证图片
        if (img == null) {
            return Response.paramErr("图片不能为空");
        }
        if (img.getSize() > (1 * 1024 * 1024)) {
            return Response.paramErr("单张图片大小不能超过 1MB");
        }
        String extName = FilenameUtils.getExtension(img.getOriginalFilename());
        if (StringUtils.containsAny(ALLOW_IMG_TYPE, extName)) {
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
}
