package com.liangfeng.study.core.test;


import com.liangfeng.study.core.helper.HttpClientHelper;
import com.liangfeng.study.core.helper.InputStreamHelper;
import com.liangfeng.study.core.helper.JsonHelper;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: HttpClientHelper
 * @Description:
 * @date  2018/9/19 17:15
 */
public class HttpClientHelperTest {

    @Test
    public void testPostJson() {
        // 1.定义参数
        String url = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        String result = null;

        // 2. 发送请求
        url = "http://10.2.145.29:8008/ocr/recognize_document?word=0";
        result = HttpClientHelper.postJson(url, headers, JsonHelper.toJson(params));

        // 3. 打印结果
        System.out.println("==============================返回结果===============================");
        System.out.println(result);
        System.out.println("==============================返回结果===============================");
    }

    @Test
    public void testUploadAndDownload() throws Exception{

        // 1.定义参数
        String url = null; // 请求地址
        String filePath = "F:/App/gf/ocr/test1.jpg"; // 上传的图片
        String downloadFile = "F:/App/gf/ocr/test1.doc"; // 下载的word文档路径
        String result = null;

        // 2.测试情景
        // 2.1 测试情景一 返回json文本
        // 2.1.1 发送请求
        url = "http://10.2.145.29:8008/ocr/recognize_document?word=0";
        result = HttpClientHelper.upload(new File(filePath), url, null);

        // 2.1.2 打印结果
        System.out.println("==============================返回结果===============================");
        System.out.println(result);
        System.out.println("==============================返回结果===============================");

        // 2.2.测试情景e二 返回word文档
        // 2.2.1 发送请求
        url = "http://10.2.145.29:8008/ocr/recognize_document?word=1";
        result = HttpClientHelper.upload(new File(filePath), url, null);

        // 2.2.2 保存本地
        FileUtils.copyToFile(InputStreamHelper.stringToInputStream(result),new File(downloadFile));

    }

}
