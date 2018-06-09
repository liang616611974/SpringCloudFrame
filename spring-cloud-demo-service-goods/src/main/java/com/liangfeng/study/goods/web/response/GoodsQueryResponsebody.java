package com.liangfeng.study.goods.web.response;


import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.web.dto.response.QueryPageResponsebody;

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
 * @Title: GoodsQueryResponsebody
 * @Description:
 * @date 2018-06-09
 */
@Data
public class GoodsQueryResponsebody extends QueryPageResponsebody<GoodsGetResponsebody>{


    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}

}


