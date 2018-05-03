package com.liangfeng.study.core.web.dto.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: AddOrMdfRequest
 * @Description:
 * @date  2018/4/13 11:17
 */
@Data
public class AddOrMdfRequestbody extends BaseRequestbody {

    @ApiModelProperty(value = "主键")
    @NotNull(groups = {Request.Modify.class,Request.Get.class})
    private Long id;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date modifyTime;

    @ApiModelProperty(value = "创建用户主键",hidden = true)
    private Long createUser;

    @ApiModelProperty(value = "修改用户主键",hidden = true)
    private Long modifyUser;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
