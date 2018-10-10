package com.liangfeng.study.core.web.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: PageResponseBody
 * @Description:
 * @date  2018/4/11 17:47
 */
@Data
public class QueryPageResponsebody<T> {
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private long total;

    /**
     * 数据集合
     */
    @ApiModelProperty(value = "数据集合")
    private List<T> rows;

    public QueryPageResponsebody(){
        rows = new ArrayList<>();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
