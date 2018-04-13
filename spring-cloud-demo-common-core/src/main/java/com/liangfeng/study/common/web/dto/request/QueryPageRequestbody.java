package com.liangfeng.study.common.web.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: QueryPageRequestbody
 * @Description:
 * @date  2018/4/13 11:42
 */
@Data
public class QueryPageRequestbody extends BaseRequestbody{

    @ApiModelProperty(value = "页码")
    @JsonProperty("page")
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示数量")
    @JsonProperty("rows")
    private Integer pageSize;


}
