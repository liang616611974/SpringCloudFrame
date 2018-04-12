package com.liangfeng.study.goods.web.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.liangfeng.study.common.constant.AppConstant;
import com.liangfeng.study.common.web.dto.Request;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsGetResponseBody
 * @Description:
 * @date  2018/4/10 10:25
 */
public class GoodsGetResponseBody {

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品类型")
    private Integer type;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "生产商名称")
    private String producerName;

    @ApiModelProperty(value = "生产日期")
    @JsonFormat(pattern = AppConstant.PATTERN_DATE,locale = AppConstant.LOCALE)
    private Date produceDate;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = AppConstant.PATTERN_DATETIME,locale = AppConstant.LOCALE)
    private Date modifyTime;

    @ApiModelProperty(value = "创建用户主键")
    private Long createUser;

    @ApiModelProperty(value = "修改用户主键")
    private Long modifyUser;



}
