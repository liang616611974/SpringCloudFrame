package com.liangfeng.study.goods.web.request;


import com.liangfeng.study.common.web.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsSaveRequestBody
 * @Description:
 * @date  2018/4/9 16:36
 */
@Data
public class GoodsSaveOrUptRequestBody {

    @ApiModelProperty(value = "商品主键")
    @NotNull(groups = {Request.Modify.class})
    private Long id;

    @ApiModelProperty(value = "商品名称")
    @NotNull(groups = {Request.add.class})
    private String goodsName;

    @ApiModelProperty(value = "商品类型")
    @NotNull(groups = {Request.add.class})
    private Integer type;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "卖家id")
    private Long sellUserId;
}
