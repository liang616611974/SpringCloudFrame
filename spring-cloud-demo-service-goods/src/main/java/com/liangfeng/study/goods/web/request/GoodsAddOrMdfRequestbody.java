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
 * @date 2018-07-26
 */
@Data
public class GoodsAddOrMdfRequestbody extends AddOrMdfRequestbody{
	
    /**
     * 商品名称 goods_name
     */
    @ApiModelProperty(value = "商品名称", example = "商品名称")
    private String goodsName;

    /**
     * 商品类型 goods_type
     */
    @ApiModelProperty(value = "商品类型", example = "商品类型")
    private String goodsType;

    /**
     * 价格 price
     */
    @ApiModelProperty(value = "价格", example = "1")
    private BigDecimal price;

    /**
     * 生产商 producer
     */
    @ApiModelProperty(value = "生产商", example = "生产商")
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
    private String imgUrl;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

