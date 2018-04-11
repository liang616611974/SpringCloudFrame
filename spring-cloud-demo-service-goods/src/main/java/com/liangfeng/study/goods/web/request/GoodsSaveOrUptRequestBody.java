package com.liangfeng.study.goods.web.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.liangfeng.study.common.constant.AppConstant;
import com.liangfeng.study.common.web.dto.BaseRequestBody;
import com.liangfeng.study.common.web.dto.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsSaveRequestBody
 * @Description:
 * @date  2018/4/9 16:36
 */
@Data
public class GoodsSaveOrUptRequestBody extends BaseRequestBody{

    @ApiModelProperty(value = "商品名称")
    @NotNull(groups = {Request.add.class})
    private String goodsName;

    @ApiModelProperty(value = "商品类型")
    @NotNull(groups = {Request.add.class})
    private Integer type;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "生产商名称")
    private String producerName;

    @ApiModelProperty(value = "生产日期")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE)
    private Date produceDate;
}
