package com.liangfeng.study.common.web.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liangfeng.study.common.component.id.IdGenerator;
import com.liangfeng.study.common.helper.SpringContextHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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
public class BaseRequestBody {

    @Autowired
    IdGenerator idGenerator;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @NotNull(groups = {Request.Modify.class,Request.Remove.class,Request.Get.class})
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建时间")
    private Date modifyTime;

    @ApiModelProperty(value = "创建用户主键")
    private Long createUser;

    @ApiModelProperty(value = "修改用户主键")
    private Long modifyUser;

    /**
     * 角色主键
     */
    @ApiModelProperty(value = "角色主键")
    private Long userId;

    /**
     * 角色主键集合
     */
    @ApiModelProperty(value = "角色主键集合")
    private List<Long> userRoleIds;

    public Long getId(){return id;}

    public void setId(Long id) {
        if(id==null){
            this.id = idGenerator.generateId();
        }else {
            this.id = id;
        }
    }

    public Date getCreateTime() {
        return new Date();
    }


    public Date getModifyTime() {
        return new Date();
    }

}
