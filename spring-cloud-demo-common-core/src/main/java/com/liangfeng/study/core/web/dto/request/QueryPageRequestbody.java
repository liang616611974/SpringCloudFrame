package com.liangfeng.study.core.web.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: QueryPageRequestbody
 * @Description:
 * @date  2018/4/13 11:42
 */
@Data
public class QueryPageRequestbody extends BaseRequestbody{

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    @JsonProperty("page")
    private int pageNum;

    /**
     * 每页显示数量
     */
    @ApiModelProperty(value = "每页显示数量")
    @JsonProperty("rows")
    private int pageSize;

    /**
     * 排序方式,例如：create_time desc modify_time desc
     */
    @ApiModelProperty(value = "排序方式", example = "create_time desc modify_time desc")
    private String sortColumns;

    /**
     * 分页开始索引
     */
    @ApiModelProperty(value = "分页开始索引",hidden = true)
    @JsonIgnore
    private int begin;

    /**
     * 结束索引
     */
    @ApiModelProperty(value = "结束索引",hidden = true)
    @JsonIgnore
    private int end;

    public int getBegin() {
        return ((this.pageNum - 1) * this.pageSize);
    }

    public int getEnd() {
        return this.pageNum * this.pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
