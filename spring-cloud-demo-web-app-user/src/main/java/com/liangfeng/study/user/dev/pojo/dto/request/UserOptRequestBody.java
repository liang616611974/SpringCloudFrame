package com.liangfeng.study.user.dev.pojo.dto.request;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

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
    @ApiModelProperty(value = "用户主键")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank
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
    @NotBlank
    private String mobile;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    private String email;

}
