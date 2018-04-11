package com.liangfeng.study.common.web.dto;


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
public class GetRequestBody {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @NotNull
    private Long id;
}
