package com.liangfeng.study.goods.dao.auto.mapper;

import com.liangfeng.study.goods.dao.auto.model.pojo.Goods;
import com.liangfeng.study.goods.dao.auto.model.qo.GoodsQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsMapper
 * @Description:
 * @date  2018/4/9 17:03
 */
@Mapper
public interface GoodsMapper {
    /**
     * 保存
     * @param goods
     */
    void insert(Goods goods);

    /**
     * 修改
     * @param goods
     */
    void update(Goods goods);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 获取
     * @param id
     */
    Goods get(Long id);

    /**
     * 查询
     * @return
     */
    List<Goods> query(GoodsQuery query);

    /**
     * 分页查询
     * @return
     */
    List<Goods> queryPage(GoodsQuery query);

    /**
     * 总记录数
     * @param query
     * @return
     */
    int total(GoodsQuery query);
}
