package com.liangfeng.study.dict.model.auto.pojo;


import com.liangfeng.study.core.framework.base.BaseEntity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: Dict
 * @Description:
 * @date 2018-06-09
 */
@Data
public class Dict extends BaseEntity{
	
	/** 
	 * 主键 id
	 */
	private Long id;
	
	/** 
	 * 字典组编号 group_code
	 */
	private String groupCode;
	
	/** 
	 * 字典编号 dict_code
	 */
	private String dictCode;
	
	/** 
	 * 系统编码 sys_code
	 */
	private String sysCode;
	
	/** 
	 * 字典组描述 group_desc
	 */
	private String groupDesc;
	
	/** 
	 * 字典描述 dict_desc
	 */
	private String dictDesc;
	
	/** 
	 * 顺序 dict_order
	 */
	private Byte dictOrder;
	
	/** 
	 * 是否使用 is_use
	 */
	private Boolean isUse;
	
	/** 
	 * 创建用户 cre_user
	 */
	private Long creUser;
	
	/** 
	 * 创建时间 cre_time
	 */
	private Date creTime;
	
	/** 
	 * 修改用户 mdf_user
	 */
	private Long mdfUser;
	
	/** 
	 * 修改时间 mdf_time
	 */
	private Date mdfTime;
	

}

