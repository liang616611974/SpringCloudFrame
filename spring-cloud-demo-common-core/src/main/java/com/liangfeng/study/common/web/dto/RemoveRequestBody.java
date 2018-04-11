package com.liangfeng.study.common.web.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: RemoveRequestBody 删除请求参数对象
 * @Description:
 * @date  2018/4/9 18:19
 */
@Data
public class RemoveRequestBody {
    /**
     * 主键集合
     */
    @ApiModelProperty(value = "主键集合",example = "id1,id2")
    @NotEmpty
    private List<Long> ids;
}
