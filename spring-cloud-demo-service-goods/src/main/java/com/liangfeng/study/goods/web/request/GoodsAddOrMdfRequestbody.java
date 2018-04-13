package com.liangfeng.study.goods.web.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.liangfeng.study.common.constant.AppConstant;
import com.liangfeng.study.common.web.dto.request.AddOrMdfRequestbody;
import com.liangfeng.study.common.web.dto.request.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
public class GoodsAddOrMdfRequestbody extends AddOrMdfRequestbody {

    @ApiModelProperty(value = "商品名称",example = "耐克跑鞋")
    @NotNull(groups = {Request.Add.class})
    private String goodsName;

    @ApiModelProperty(value = "商品类型",example = "运动系列")
    @NotNull(groups = {Request.Add.class})
    private String type;

    @ApiModelProperty(value = "商品价格",example = "899")
    private BigDecimal price;

    @ApiModelProperty(value = "生产商名称",example = "耐克集团")
    private String producerName;

    @ApiModelProperty(value = "生产日期",example = "2018-01-01")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date produceDate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
