package com.liangfeng.study.dict.service.impl;

import com.liangfeng.study.core.dto.ImageBO;
import com.liangfeng.study.dict.service.CommonService;
import com.liangfeng.study.dict.web.response.ImgUploadResponsebody;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonServiceTest {

    @Autowired
    CommonService service;

    @Test
    public void uploadImg() throws Exception {
        // 1.定义参数
        File file;
        FileInputStream inputStream;
        ImageBO imageBO;
        ImgUploadResponsebody responsebody;
        String errMsg = "测试上传图片失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        imageBO = new ImageBO();
        responsebody = new ImgUploadResponsebody();
        file = new File("C:\\Users\\Administrator\\Desktop\\syc.jpg");
        inputStream = new FileInputStream(file);
        FileChannel fc = inputStream.getChannel();
        imageBO.setSuffix("jpg");
        imageBO.setSize(fc.size());
        imageBO.setInputStream(inputStream);
        imageBO.setBytes(FileUtils.readFileToByteArray(file));
        // 执行
        responsebody = service.uploadImg(imageBO);
        // 验证结果
        Assert.assertNotNull(errMsg,responsebody.getUrl());
    }

}