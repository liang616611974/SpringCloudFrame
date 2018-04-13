package com.liangfeng.study.common.web.dto.request;


import com.liangfeng.study.common.component.id.IdGenerator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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

    /**
     * 创建用户主键
     */
    @ApiModelProperty(value = "创建用户主键",hidden = true)
    private Long createUser;

    /**
     * 修改用户主键
     */
    @ApiModelProperty(value = "修改用户主键",hidden = true)
    private Long modifyUser;

    /*public Long getId(){return id;}

    public void setId(Long id) {
        if(id==null){
            this.id = idGenerator.generateId();
        }else {
            this.id = id;
        }
    }*/
}
