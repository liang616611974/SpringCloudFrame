

package com.liangfeng.study.goods.mapper;


import java.util.List;

import com.liangfeng.study.core.framework.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.liangfeng.study.goods.model.auto.pojo.Goods;
import com.liangfeng.study.goods.model.auto.qo.GoodsQuery;





/**
* @Title: GoodsMapper
* @Description:
* @author Liangfeng
* @date 2018-04-21
* @version 1.0
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods,GoodsQuery,Long>{



}
