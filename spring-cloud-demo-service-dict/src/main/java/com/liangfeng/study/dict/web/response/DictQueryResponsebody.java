package com.liangfeng.study.dict.web.response;


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
 * @Title: DictQueryResponsebody
 * @Description:
 * @date 2018-07-03
 */
@Data
public class DictQueryResponsebody extends QueryPageResponsebody<DictGetResponsebody>{


    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}

}


