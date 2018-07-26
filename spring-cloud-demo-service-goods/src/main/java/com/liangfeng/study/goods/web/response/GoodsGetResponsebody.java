package com.liangfeng.study.goods.web.response;


import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.helper.ExcelHelper.ExcelColumn;
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
 * @Title: GoodsGetResponsebody
 * @Description:
 * @date 2018-07-26
 */
@Data
public class GoodsGetResponsebody {

    /**
     * 主键 id
     */
    @ExcelColumn(name="主键")
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    /**
     * 商品名称 goods_name
     */
    @ExcelColumn(name="商品名称")
    @ApiModelProperty(value = "商品名称", example = "商品名称")
    private String goodsName;

    /**
     * 商品类型 goods_type
     */
    @ExcelColumn(name="商品类型")
    @ApiModelProperty(value = "商品类型", example = "商品类型")
    private String goodsType;

    /**
     * 价格 price
     */
    @ExcelColumn(name="价格")
    @ApiModelProperty(value = "价格", example = "1")
    private BigDecimal price;

    /**
     * 生产商 producer
     */
    @ExcelColumn(name="生产商")
    @ApiModelProperty(value = "生产商", example = "生产商")
    private String producer;

    /**
     * 生产日期 produce_date
     */
    @ExcelColumn(name="生产日期")
    @ApiModelProperty(value = "生产日期", example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDate;

    /**
     * 图片 img_url
     */
    @ExcelColumn(name="图片")
    @ApiModelProperty(value = "图片", example = "图片")
    private String imgUrl;

    /**
     * 创建时间 cre_time
     */
    @ExcelColumn(name="创建时间")
    @ApiModelProperty(value = "创建时间", example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date creTime;

    /**
     * 修改时间 mdf_time
     */
    @ExcelColumn(name="修改时间")
    @ApiModelProperty(value = "修改时间", example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date mdfTime;

    /**
     * 创建用户 cre_user
     */
    @ExcelColumn(name="创建用户")
    @ApiModelProperty(value = "创建用户", example = "1")
    private Long creUser;

    /**
     * 修改用户 mdf_user
     */
    @ExcelColumn(name="修改用户")
    @ApiModelProperty(value = "修改用户", example = "1")
    private Long mdfUser;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

