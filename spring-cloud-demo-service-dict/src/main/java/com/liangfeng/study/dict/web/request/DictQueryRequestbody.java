package com.liangfeng.study.dict.web.request;


import com.liangfeng.study.core.web.dto.request.QueryPageRequestbody;
import com.liangfeng.study.core.constant.AppConstant;

import java.util.Date;
import java.math.BigDecimal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictQueryRequestbody
 * @Description:
 * @date 2018-06-09
 */
@Data
public class DictQueryRequestbody extends QueryPageRequestbody{

    @ApiModelProperty(value = "字典组编号",example = "字典组编号")
    private String groupCode;

    @ApiModelProperty(value = "字典编号",example = "字典编号")
    private String dictCode;

    @ApiModelProperty(value = "系统编码",example = "系统编码")
    private String sysCode;

    @ApiModelProperty(value = "是否使用",example = "")
    private Boolean isUse;


   /*
    @ApiModelProperty(value = "主键",example = "")
    private Long id;

    @ApiModelProperty(value = "字典组描述",example = "字典组描述")
    private String groupDesc;

    @ApiModelProperty(value = "字典描述",example = "字典描述")
    private String dictDesc;

    @ApiModelProperty(value = "顺序",example = "")
    private Byte dictOrder;

   @ApiModelProperty(value = "创建用户",example = "")
    private Long creUser;

    @ApiModelProperty(value = "创建时间开始",example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date creTimeBegin;

    @ApiModelProperty(value = "创建时间结束",example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date creTimeEnd;

    @ApiModelProperty(value = "修改用户",example = "")
    private Long mdfUser;

    @ApiModelProperty(value = "修改时间开始",example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date mdfTimeBegin;

    @ApiModelProperty(value = "修改时间结束",example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date mdfTimeEnd;*/

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

