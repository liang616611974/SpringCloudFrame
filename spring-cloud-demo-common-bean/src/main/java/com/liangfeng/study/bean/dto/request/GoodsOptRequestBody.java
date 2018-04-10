package com.liangfeng.study.bean.dto.request;


import com.liangfeng.study.common.web.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsOptRequestBody
 * @Description:
 * @date  2017/11/1 15:59
 */
@Data
public class GoodsOptRequestBody {

    /**
     * 商品主键
     */
    @ApiModelProperty(value = "商品主键,修改、删除、获取必传")
    @NotNull(groups = {Request.Modify.class,Request.Remove.class,Request.Get.class})
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @NotNull(groups = {Request.Create.class})
    private String goodsName;

    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型",allowableValues = "1-食物,2-玩具,3-家庭用品")
    @NotNull(groups = {Request.Create.class})
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
