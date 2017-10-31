package com.liangfeng.study.api.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserResponseBody
 * @Description:
 * @date  2017/10/30 15:34
 */
@Data
public class UserResponseBody {

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
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
