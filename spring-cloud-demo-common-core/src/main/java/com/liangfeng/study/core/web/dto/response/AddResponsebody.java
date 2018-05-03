package com.liangfeng.study.core.web.dto.response;


import com.liangfeng.study.core.web.dto.request.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: AddOrMdfResponsebody
 * @Description:
 * @date  2018/5/1 0001 上午 12:35
 */
@Data
public class AddResponsebody {

    @ApiModelProperty(value = "主键")
    private Long id;

    public AddResponsebody(Long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
