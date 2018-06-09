package com.liangfeng.study.goods.web.request;


import com.liangfeng.study.core.web.dto.request.AddOrMdfRequestbody;
import com.liangfeng.study.core.constant.AppConstant;

import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsAddOrMdfRequestbody
 * @Description:
 * @date 2018-06-09
 */
@Data
public class GoodsAddOrMdfRequestbody extends AddOrMdfRequestbody{
	
    @ApiModelProperty(value = "商品名称",example = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品类型",example = "商品类型")
    private String goodsType;

    @ApiModelProperty(value = "价格",example = "")
    private BigDecimal price;

    @ApiModelProperty(value = "生产商",example = "生产商")
    private String producer;

    @ApiModelProperty(value = "生产日期",example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDate;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

