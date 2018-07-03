package com.liangfeng.study.dict.web.response;


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
 * @Title: DictGetResponsebody
 * @Description:
 * @date 2018-07-03
 */
@Data
public class DictGetResponsebody {

    /**
     * 主键 id
     */
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    /**
     * 字典组编号 group_code
     */
    @ApiModelProperty(value = "字典组编号", example = "字典组编号")
    private String groupCode;

    /**
     * 字典编号 dict_code
     */
    @ApiModelProperty(value = "字典编号", example = "字典编号")
    private String dictCode;

    /**
     * 系统编码 sys_code
     */
    @ApiModelProperty(value = "系统编码", example = "系统编码")
    private String sysCode;

    /**
     * 字典组描述 group_desc
     */
    @ApiModelProperty(value = "字典组描述", example = "字典组描述")
    private String groupDesc;

    /**
     * 字典描述 dict_desc
     */
    @ApiModelProperty(value = "字典描述", example = "字典描述")
    private String dictDesc;

    /**
     * 顺序 dict_order
     */
    @ApiModelProperty(value = "顺序", example = "1")
    private Byte dictOrder;

    /**
     * 是否使用 is_use
     */
    @ApiModelProperty(value = "是否使用", example = "true")
    private Boolean isUse;

    /**
     * 创建用户 cre_user
     */
    @ApiModelProperty(value = "创建用户", example = "1")
    private Long creUser;

    /**
     * 创建时间 cre_time
     */
    @ApiModelProperty(value = "创建时间", example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date creTime;

    /**
     * 修改用户 mdf_user
     */
    @ApiModelProperty(value = "修改用户", example = "1")
    private Long mdfUser;

    /**
     * 修改时间 mdf_time
     */
    @ApiModelProperty(value = "修改时间", example = "2018-01-01 00:00:00")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE,timezone = AppConstant.TIMEZONE)
    private Date mdfTime;

    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}


}

