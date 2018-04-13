package com.liangfeng.study.common.web.dto.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty(value = "主键")
    @NotNull
    private Long id;
}
