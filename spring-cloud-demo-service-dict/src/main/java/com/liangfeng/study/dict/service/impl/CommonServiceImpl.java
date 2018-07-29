package com.liangfeng.study.dict.service.impl;


import com.liangfeng.study.core.dto.ImageBO;
import com.liangfeng.study.core.helper.DateHelper;
import com.liangfeng.study.dict.service.CommonService;
import com.liangfeng.study.dict.web.response.ImgUploadResponsebody;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: CommonServiceImply
 * @Description:
 * @date  2018/7/28 0028 下午 3:18
 */
@Service
public class CommonServiceImpl implements CommonService{

    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Value(value = "${app.img.savePath}")
    private String imgSavePath;

    @Value(value = "${app.img.urlPath}")
    private String imgUrlPath;

    @Override
    public ImgUploadResponsebody uploadImg(ImageBO imgBO) {

        // 1.定义参数
        ImgUploadResponsebody respBody = new ImgUploadResponsebody();
        // 2.保存图片
        Date now = new Date();
        File file = null;
        OutputStream outputStream = null;
        StringBuilder savePath = new StringBuilder();
        savePath.append(DateHelper.formatDate(now)).append("\\");
        try{
            // 创建目录
            file = new File(imgSavePath + savePath.toString());
            if(!file.exists()){
                file.mkdirs();
            }
            // 保存图片
            savePath.append(now.getTime()).append(".").append(imgBO.getSuffix());
            file = new File(imgSavePath + savePath.toString());
            outputStream = new FileOutputStream(file);
            IOUtils.copy(imgBO.getInputStream(),outputStream);
        }catch (Exception e){
            logger.error("保存图片发生异常",e);
            throw new RuntimeException("保存图片发生异常", e);
        }

        // 3.返回结果
        //respBody.setId();
        respBody.setUrl(imgUrlPath + savePath);
        logger.debug("图片Url:{}",respBody.getUrl());
        return respBody;
    }
}
