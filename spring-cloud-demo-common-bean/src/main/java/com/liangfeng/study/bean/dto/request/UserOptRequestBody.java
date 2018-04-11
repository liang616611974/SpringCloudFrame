package com.liangfeng.study.bean.dto.request;


import com.liangfeng.study.common.web.dto.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserOptRequestBody
 * @Description:
 * @date  2017/10/27 16:56
 */
@Data
public class UserOptRequestBody {

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键,修改必传")
    @NotNull(groups = {Request.Modify.class,Request.Remove.class,Request.Get.class})
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名,新增必传")
    @NotBlank(groups = {Request.Create.class})
    private String username;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机",required = true)
    @NotBlank(groups = {Request.Create.class,Request.Modify.class})
    private String mobile;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}
