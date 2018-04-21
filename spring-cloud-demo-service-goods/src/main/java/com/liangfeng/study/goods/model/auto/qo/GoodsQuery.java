

package com.liangfeng.study.goods.model.auto.qo;


import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.liangfeng.study.core.framework.base.BaseQuery;



/**
* @Title: GoodsQuery.java
* @Description:
* @author Liangfeng
* @date 2018-04-21
* @version 1.0
 */
@Data
public class GoodsQuery extends BaseQuery {


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
     * 生产日期开始 produce_date
     */
	private Date produceDateBegin;
	/**
     * 生产日期结束 produce_date
     */
	private Date produceDateEnd;

	/**
     * 创建时间开始 create_time
     */
	private Date createTimeBegin;
	/**
     * 创建时间结束 create_time
     */
	private Date createTimeEnd;

	/**
     * 修改时间开始 modify_time
     */
	private Date modifyTimeBegin;
	/**
     * 修改时间结束 modify_time
     */
	private Date modifyTimeEnd;

	/**
     * 创建用户主键 create_user
     */
	private Long createUser;

	/**
     * 修改用户主键 modify_user
     */
	private Long modifyUser;

}







