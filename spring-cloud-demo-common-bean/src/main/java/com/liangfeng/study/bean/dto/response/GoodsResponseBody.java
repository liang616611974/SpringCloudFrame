package com.liangfeng.study.bean.dto.response;


import com.liangfeng.study.common.dto.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsResponseBody
 * @Description:
 * @date  2017/11/1 15:59
 */
@Data
public class GoodsResponseBody {

    /**
     * 商品主键
     */
    @ApiModelProperty(value = "商品主键,修改、删除、获取必传")
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型",allowableValues = "1-食物,2-玩具,3-家庭用品")
    private Integer type;

    /**
     * 价格
     */
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    /**
     * 卖家id
     */
    @ApiModelProperty(value = "卖家id")
    private Long sellUserId;

    /**
     * 卖家名称
     */
    @ApiModelProperty(value = "卖家名称")
    private String sellUsername;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
