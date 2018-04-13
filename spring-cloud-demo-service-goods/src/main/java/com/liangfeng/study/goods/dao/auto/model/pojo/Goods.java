package com.liangfeng.study.goods.dao.auto.model.pojo;


import com.liangfeng.study.common.framework.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: Goods
 * @Description:
 * @date  2018/4/9 17:03
 */
@Data
public class Goods extends BaseEntity{

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品类型
     */
    private String type;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 生产商名称
     */
    private String producerName;

    /**
     * 生产日期
     */
    private Date produceDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建用户主键
     */
    private Long createUser;

    /**
     * 修改用户主键
     */
    private Long modifyUser;

}
