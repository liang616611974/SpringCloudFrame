package com.liangfeng.study.common.web;


import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: RemoveRequestBody 删除请求参数对象
 * @Description:
 * @date  2018/4/9 18:19
 */
public class RemoveRequestBody {
    /**
     * 主键集合
     */
    @ApiModelProperty(value = "主键集合",example = "id1,id2")
    private List<Long> ids;
}
