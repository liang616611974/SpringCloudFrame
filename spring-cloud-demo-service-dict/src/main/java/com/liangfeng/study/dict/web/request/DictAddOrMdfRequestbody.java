package com.liangfeng.study.dict.web.request;


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
 * @Title: DictAddOrMdfRequestbody
 * @Description:
 * @date 2018-06-09
 */
@Data
public class DictAddOrMdfRequestbody extends AddOrMdfRequestbody{
	
    @ApiModelProperty(value = "字典组编号",example = "字典组编号")
    private String groupCode;

    @ApiModelProperty(value = "字典编号",example = "字典编号")
    private String dictCode;

    @ApiModelProperty(value = "系统编码",example = "系统编码")
    private String sysCode;

    @ApiModelProperty(value = "字典组描述",example = "字典组描述")
    private String groupDesc;

    @ApiModelProperty(value = "字典描述",example = "字典描述")
    private String dictDesc;

    @ApiModelProperty(value = "顺序",example = "")
    private Byte dictOrder;

    @ApiModelProperty(value = "是否使用",example = "")
    private Boolean isUse;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

