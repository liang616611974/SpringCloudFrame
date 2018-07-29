package com.liangfeng.study.core.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.InputStream;

/**
 * @Title: ImageBO.java
 * @Description: 
 * @author Liangfeng
 * @date 2017/5/18 0018 下午 6:29
 * @version 1.0
 */
@Data
public class ImageBO {

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片大小
     */
    private Long size;

    /**
     * 图片后缀
     */
    private String suffix;

    /**
     * 图片链接
     */
    private String url;

    /**
     * 图片原始名称
     */
    private String originalFilename;

    /**
     * 图片字节
     */
    private byte[] bytes;

    /**
     *  图片流
     */
    private InputStream inputStream;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}