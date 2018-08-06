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
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.NotNull;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsAddOrMdfRequestbody
 * @Description:
 * @date 2018-08-06
 */
@Data
public class GoodsAddOrMdfRequestbody extends AddOrMdfRequestbody{
	
    /**
     * 商品名称 goods_name
     */
    @ApiModelProperty(value = "商品名称", example = "商品名称")
    @NotBlank 
    @Length(max=50)
    private String goodsName;

    /**
     * 商品类型(GOODS_FIRST_TYPE) goods_type
     */
    @ApiModelProperty(value = "商品类型(GOODS_FIRST_TYPE)", example = "商品类型(GOODS_FIRST_TYPE)")
    @NotBlank 
    @Length(max=35)
    private String goodsType;

    /**
     * 价格 price
     */
    @ApiModelProperty(value = "价格", example = "1")
    @NotNull
    private BigDecimal price;

    /**
     * 生产商 producer
     */
    @ApiModelProperty(value = "生产商", example = "生产商")
    @Length(max=50)
    private String producer;

    /**
     * 生产日期 produce_date
     */
    @ApiModelProperty(value = "生产日期", example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDate;

    /**
     * 图片 img_url
     */
    @ApiModelProperty(value = "图片", example = "图片")
    @Length(max=100)
    private String imgUrl;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

