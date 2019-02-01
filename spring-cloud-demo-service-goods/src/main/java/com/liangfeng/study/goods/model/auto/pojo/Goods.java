package com.liangfeng.study.goods.model.auto.pojo;


import com.liangfeng.study.core.framework.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: Goods
 * @Description:
 * @date 2018-07-26
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
	 * 商品类型 goods_type
	 */
	private String goodsType;
	
	/** 
	 * 价格 price
	 */
	private BigDecimal price;
	
	/** 
	 * 生产商 producer
	 */
	private String producer;
	
	/** 
	 * 生产日期 produce_date
	 */
	private Date produceDate;
	
	/** 
	 * 图片 img_url
	 */
	private String imgUrl;
	
	/** 
	 * 创建时间 cre_time
	 */
	private Date creTime;
	
	/** 
	 * 修改时间 mdf_time
	 */
	private Date mdfTime;
	
	/** 
	 * 创建用户 cre_user
	 */
	private Long creUser;
	
	/** 
	 * 修改用户 mdf_user
	 */
	private Long mdfUser;
}

