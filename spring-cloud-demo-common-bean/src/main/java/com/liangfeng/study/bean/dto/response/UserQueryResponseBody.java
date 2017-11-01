package com.liangfeng.study.bean.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: UserQueryResponseBody
 * @Description:
 * @date  2017/10/30 18:03
 */
@Data
public class UserQueryResponseBody {

    @ApiModelProperty(value = "用户集合")
    List<UserResponseBody> users;

}
