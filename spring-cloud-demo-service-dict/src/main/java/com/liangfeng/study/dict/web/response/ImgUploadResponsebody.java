package com.liangfeng.study.dict.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Field;

/**
 * @Title: ImgUploadRespBody.java
 * @Description: 
 * @author Liangfeng
 * @date 2017/5/18 0018 下午 6:59
 * @version 1.0
 */
@Data
public class ImgUploadResponsebody {

    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    @ApiModelProperty(value = "url", example = "1")
    private String url;

    @Override
    public String toString() {

        // 过滤某字段打印
        return new ReflectionToStringBuilder(
                this, ToStringStyle.JSON_STYLE) {
            @Override
            protected boolean accept(Field field) {
                return !field.getName().equals("passWord");
            }
        }.toString();

        //return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
