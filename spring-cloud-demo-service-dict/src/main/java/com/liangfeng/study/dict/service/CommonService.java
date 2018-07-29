package com.liangfeng.study.dict.service;

import com.liangfeng.study.core.dto.ImageBO;
import com.liangfeng.study.dict.web.response.ImgUploadResponsebody;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: CommonService
 * @Description:
 * @date  2018/7/28 0028 下午 3:17
 */
public interface CommonService {
    ImgUploadResponsebody uploadImg(ImageBO imgBO);
}
