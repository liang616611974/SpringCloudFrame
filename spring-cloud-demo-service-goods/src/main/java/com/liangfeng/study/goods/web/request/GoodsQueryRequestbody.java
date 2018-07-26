package com.liangfeng.study.goods.web.request;


import com.liangfeng.study.core.web.dto.request.QueryPageRequestbody;
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
 * @Title: GoodsQueryRequestbody
 * @Description:
 * @date 2018-07-26
 */
@Data
public class GoodsQueryRequestbody extends QueryPageRequestbody{
	
    /**
     * 主键 id
     */
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

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
     * 生产日期开始 produce_date
     */
    @ApiModelProperty(value = "生产日期开始", example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDateBegin;

    /**
     * 生产日期结束 produce_date
     */
    @ApiModelProperty(value = "生产日期结束", example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDateEnd;

    /**
     * 图片 img_url
     */
    @ApiModelProperty(value = "图片", example = "图片")
    private String imgUrl;

    /**
     * 创建时间开始 cre_time
     */
    @ApiModelProperty(value = "创建时间开始", example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date creTimeBegin;

    /**
     * 创建时间结束 cre_time
     */
    @ApiModelProperty(value = "创建时间结束", example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date creTimeEnd;

    /**
     * 修改时间开始 mdf_time
     */
    @ApiModelProperty(value = "修改时间开始", example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date mdfTimeBegin;

    /**
     * 修改时间结束 mdf_time
     */
    @ApiModelProperty(value = "修改时间结束", example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date mdfTimeEnd;

    /**
     * 创建用户 cre_user
     */
    @ApiModelProperty(value = "创建用户", example = "1")
    private Long creUser;

    /**
     * 修改用户 mdf_user
     */
    @ApiModelProperty(value = "修改用户", example = "1")
    private Long mdfUser;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

