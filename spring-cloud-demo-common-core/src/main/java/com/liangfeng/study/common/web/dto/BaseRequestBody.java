package com.liangfeng.study.common.web.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liangfeng.study.common.component.id.IdGenerator;
import com.liangfeng.study.common.constant.AppConstant;
import com.liangfeng.study.common.helper.SpringContextHelper;
import com.liangfeng.study.common.helper.WebHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

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
public class BaseRequestBody implements java.io.Serializable{

    private static final long serialVersionUID = -1L;

    @Autowired
    @ApiModelProperty(hidden = true)
    private IdGenerator idGenerator;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @NotNull(groups = {Request.Modify.class,Request.Get.class})
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    /**
     * 修改时间
     */
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

    /**
     * 角色主键
     */
    @ApiModelProperty(value = "角色主键",hidden = true)
    private Long userId;

    /**
     * 角色主键集合
     */
    @ApiModelProperty(value = "角色主键集合",hidden = true)
    private List<Long> userRoleIds;

    public Long getId(){return id;}

    public void setId(Long id) {
        if(id==null){
            this.id = idGenerator.generateId();
        }else {
            this.id = id;
        }
    }

    public void setCreateTime(Date createTime) {
        this.createTime = new Date();
    }

    public Date getCreateTime() {
        return new Date();
    }


    public Date getModifyTime() {
        return new Date();
    }

    public Long getCreateUser() {
        return WebHelper.getSession(WebHelper.getRequest(), AppConstant.SESSION_ATTR_NAME_USERID);
    }

    public Long getModifyUser() {
        return WebHelper.getSession(WebHelper.getRequest(), AppConstant.SESSION_ATTR_NAME_USERID);
    }

}
