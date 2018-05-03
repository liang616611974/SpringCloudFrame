package com.liangfeng.study.goods.web.request;


import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liangfeng.study.core.web.dto.request.AddOrMdfRequestbody;
import com.liangfeng.study.core.constant.AppConstant;


/**
* @Title: Goods
* @Description:
* @author Liangfeng
* @date 2018-04-30
* @version 1.0
 */
@Data
public class GoodsAddOrMdfRequestbody extends AddOrMdfRequestbody{
	
    @ApiModelProperty(value = "商品名称",example = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品类型",example = "商品类型")
    private String type;

    @ApiModelProperty(value = "商品价格",example = "")
    private BigDecimal price;

    @ApiModelProperty(value = "生产商名称",example = "生产商名称")
    private String producerName;

    @ApiModelProperty(value = "生产日期",example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDate;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}

}

