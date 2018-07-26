package com.liangfeng.study.goods.model.auto.qo;


import com.liangfeng.study.core.framework.base.BaseQuery;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsQuery
 * @Description:
 * @date 2018-07-26
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
     * 生产日期开始 produce_date
     */
	private Date produceDateBegin;
	/**
     * 生产日期结束 produce_date
     */
	private Date produceDateEnd;

	/**
     * 图片 img_url
     */
	private String imgUrl;

	/**
     * 创建时间开始 cre_time
     */
	private Date creTimeBegin;
	/**
     * 创建时间结束 cre_time
     */
	private Date creTimeEnd;

	/**
     * 修改时间开始 mdf_time
     */
	private Date mdfTimeBegin;
	/**
     * 修改时间结束 mdf_time
     */
	private Date mdfTimeEnd;

	/**
     * 创建用户 cre_user
     */
	private Long creUser;

	/**
     * 修改用户 mdf_user
     */
	private Long mdfUser;

}







