package com.liangfeng.study.goods.web.request;


import com.liangfeng.study.core.web.dto.request.QueryPageRequestbody;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsQueryRequestBody
 * @Description:
 * @date  2018/4/9 18:28
 */
@Data
public class GoodsQueryPageRequestbody extends QueryPageRequestbody{

    @ApiModelProperty(value = "商品名称",example = "耐克跑鞋")
    private String goodsName;

    @ApiModelProperty(value = "商品类型",example = "运动系列")
    private String type;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
