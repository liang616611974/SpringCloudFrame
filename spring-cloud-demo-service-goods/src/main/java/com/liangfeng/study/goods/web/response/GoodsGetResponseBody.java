package com.liangfeng.study.goods.web.response;


import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsGetResponseBody
 * @Description:
 * @date  2018/4/10 10:25
 */
public class GoodsGetResponseBody {

    /**
     * 商品主键
     */
    @ApiModelProperty(value = "商品主键")
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



}
