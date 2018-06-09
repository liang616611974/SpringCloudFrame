package com.liangfeng.study.goods.mapper;


import com.liangfeng.study.core.framework.base.BaseMapper;
import com.liangfeng.study.goods.model.auto.pojo.Goods;
import com.liangfeng.study.goods.model.auto.qo.GoodsQuery;

import org.apache.ibatis.annotations.Mapper;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsMapper
 * @Description:
 * @date 2018-06-09
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods,GoodsQuery,Long> {


}
