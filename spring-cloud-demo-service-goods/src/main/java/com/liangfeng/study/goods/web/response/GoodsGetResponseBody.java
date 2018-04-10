package com.liangfeng.study.goods.web.response;


import com.liangfeng.study.common.web.Request;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
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
    @ApiModelProperty(value = "商品主键,修改、删除、获取必传")
    @NotNull(groups = {Request.Modify.class})
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @NotNull(groups = {Request.add.class})
    private String goodsName;

    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型",allowableValues = "1-食物,2-玩具,3-家庭用品")
    @NotNull(groups = {Request.add.class})
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

}
