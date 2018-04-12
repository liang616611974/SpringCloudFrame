package com.liangfeng.study.common.web.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: PageResponseBody
 * @Description:
 * @date  2018/4/11 17:47
 */
@Data
public class QueryPageResponseBody<T> {
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private int total;

    /**
     * 数据集合
     */
    @ApiModelProperty(value = "数据集合")
    private List<T> rows;
}
