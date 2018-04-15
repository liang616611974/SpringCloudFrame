package com.liangfeng.study.core.web.dto.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
public class RemoveRequestbody extends BaseRequestbody {
    /**
     * 主键集合
     */
    @ApiModelProperty(value = "主键集合")
    @NotEmpty
    private List<Long> ids;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
