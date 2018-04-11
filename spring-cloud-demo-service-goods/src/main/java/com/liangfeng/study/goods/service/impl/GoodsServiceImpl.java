package com.liangfeng.study.goods.service.impl;


import com.liangfeng.study.common.component.id.IdGenerator;
import com.liangfeng.study.common.web.dto.GetRequestBody;
import com.liangfeng.study.common.web.dto.RemoveRequestBody;
import com.liangfeng.study.goods.dao.auto.mapper.GoodsMapper;
import com.liangfeng.study.goods.dao.auto.model.pojo.Goods;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsQueryRequestBody;
import com.liangfeng.study.goods.web.request.GoodsSaveOrUptRequestBody;
import com.liangfeng.study.goods.web.response.GoodsGetResponseBody;
import com.liangfeng.study.goods.web.response.GoodsQueryResponseBody;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsServiceImpl
 * @Description:
 * @date  2018/4/9 16:50
 */
@Transactional
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public void add(GoodsSaveOrUptRequestBody requestBody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestBody,goods);
        // 3.插入数据
        goodsMapper.insert(goods);
    }

    @Override
    public void modify(GoodsSaveOrUptRequestBody requestBody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestBody,goods);
        // 3.修改数据
        goodsMapper.update(goods);
    }

    @Override
    public void remove(RemoveRequestBody requestBody) {
        // 1.遍历删除
        for (Long id : requestBody.getIds()) {
            goodsMapper.delete(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public GoodsGetResponseBody get(GetRequestBody requestBody) {
        // 1.定义参数
        GoodsGetResponseBody responseBody = new GoodsGetResponseBody();
        // 2.查询对象
        Goods goods = goodsMapper.get(requestBody.getId());
        // 3.复制属性
        BeanUtils.copyProperties(goods,responseBody);
        // 4.返回数据
        return responseBody;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public GoodsQueryResponseBody query(GoodsQueryRequestBody requestBody) {
        return null;
    }
}
