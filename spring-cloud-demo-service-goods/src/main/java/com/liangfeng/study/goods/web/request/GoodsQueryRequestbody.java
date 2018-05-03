package com.liangfeng.study.goods.web.request;


import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liangfeng.study.core.web.dto.request.QueryPageRequestbody;
import com.liangfeng.study.core.constant.AppConstant;


/**
* @Title: Goods
* @Description:
* @author Liangfeng
* @date 2018-05-01
* @version 1.0
 */
@Data
public class GoodsQueryRequestbody extends QueryPageRequestbody{
	
    @ApiModelProperty(value = "主键",example = "")
    private Long id;

    @ApiModelProperty(value = "商品名称",example = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品类型",example = "商品类型")
    private String type;

    @ApiModelProperty(value = "商品价格",example = "")
    private BigDecimal price;

    @ApiModelProperty(value = "生产商名称",example = "生产商名称")
    private String producerName;

    @ApiModelProperty(value = "生产日期开始",example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDateBegin;

    @ApiModelProperty(value = "生产日期结束",example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDateEnd;

    @ApiModelProperty(value = "创建时间开始",example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date createTimeBegin;

    @ApiModelProperty(value = "创建时间结束",example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date createTimeEnd;

    @ApiModelProperty(value = "修改时间开始",example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date modifyTimeBegin;

    @ApiModelProperty(value = "修改时间结束",example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date modifyTimeEnd;

    @ApiModelProperty(value = "创建用户主键",example = "")
    private Long createUser;

    @ApiModelProperty(value = "修改用户主键",example = "")
    private Long modifyUser;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

