package com.liangfeng.study.core.web.dto.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GetRequestBody
 * @Description:
 * @date  2018/4/10 10:16
 */
@Data
public class GetRequestbody extends BaseRequestbody {

    @ApiModelProperty(value = "主键",required = true)
    @NotNull
    private Long id;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
