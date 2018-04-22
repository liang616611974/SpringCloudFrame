

package com.liangfeng.study.goods.model.auto.pojo;


import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.liangfeng.study.core.framework.base.BaseEntity;


/**
* @Title: Goods
* @Description:
* @author Liangfeng
* @date 2018-04-22
* @version 1.0
 */
@Data
public class Goods extends BaseEntity{
	
	/** 
	 * 主键 id
	 */
	private Long id;
	
	/** 
	 * 商品名称 goods_name
	 */
	private String goodsName;
	
	/** 
	 * 商品类型 type
	 */
	private String type;
	
	/** 
	 * 商品价格 price
	 */
	private BigDecimal price;
	
	/** 
	 * 生产商名称 producer_name
	 */
	private String producerName;
	
	/** 
	 * 生产日期 produce_date
	 */
	private Date produceDate;
	
	/** 
	 * 创建时间 create_time
	 */
	private Date createTime;
	
	/** 
	 * 修改时间 modify_time
	 */
	private Date modifyTime;
	
	/** 
	 * 创建用户主键 create_user
	 */
	private Long createUser;
	
	/** 
	 * 修改用户主键 modify_user
	 */
	private Long modifyUser;
	

}

