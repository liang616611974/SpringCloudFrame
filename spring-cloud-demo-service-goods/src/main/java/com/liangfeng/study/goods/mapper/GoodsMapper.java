

package com.liangfeng.study.goods.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.liangfeng.study.core.framework.base.BaseMapper;
import com.liangfeng.study.goods.model.auto.pojo.Goods;
import com.liangfeng.study.goods.model.auto.qo.GoodsQuery;





/**
* @Title: GoodsMapper
* @Description:
* @author Liangfeng
* @date 2018-04-22
* @version 1.0
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods,GoodsQuery,Long> {


}
