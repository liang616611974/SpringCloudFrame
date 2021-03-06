package com.liangfeng.study.bean.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: OrderResponseBody
 * @Description:
 * @date  2017/10/31 17:09
 */
@Data
public class OrderResponseBody {

    /**
     * 订单主键
     */
    @ApiModelProperty(value = "订单主键,修改、删除、获取必传")
    private Long id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 卖家id
     */
    @ApiModelProperty(value = "卖家id")
    private Long sellUserId;

    /**
     * 买家id
     */
    @ApiModelProperty(value = "买家id")
    private Long buyUserId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型",allowableValues = "1-在线付款,2-货到付款")
    private Integer orderType;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态",allowableValues = "1-未付款,2-已付款")
    private Integer orderStatus;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    /**
     * 卖家名称
     */
    @ApiModelProperty(value = "卖家名称")
    private String sellUsername;

    /**
     * 买家名称
     */
    @ApiModelProperty(value = "买家名称")
    private String buyUsername;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
