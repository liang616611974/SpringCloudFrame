package com.liangfeng.study.common.web.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liangfeng.study.common.component.id.IdGenerator;
import com.liangfeng.study.common.constant.AppConstant;
import com.liangfeng.study.common.helper.WebHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: BaseRequestBody
 * @Description:
 * @date  2018/4/11 19:19
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequestbody implements java.io.Serializable{

    private static final long serialVersionUID = -4528872878867427091L;

    @ApiModelProperty(value = "角色主键",hidden = true)
    private Long userId;

    @ApiModelProperty(value = "角色主键集合",hidden = true)
    private List<Long> userRoleIds;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
