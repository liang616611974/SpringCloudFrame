package com.liangfeng.study.goods.service.impl;


import com.liangfeng.study.common.web.dto.GetRequestBody;
import com.liangfeng.study.common.web.dto.RemoveRequestBody;
import com.liangfeng.study.goods.dao.auto.mapper.GoodsMapper;
import com.liangfeng.study.goods.dao.auto.model.pojo.Goods;
import com.liangfeng.study.goods.dao.auto.model.qo.GoodsQuery;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsServiceImpl
 * @Description:
 * @date  2018/4/9 16:50
 */
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//@Service
public class GoodsJDBCServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    //@Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(GoodsSaveOrUptRequestBody requestBody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestBody,goods);
        // 3.插入数据
        goodsMapper.insert(goods);
    }

    //@Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void modify(GoodsSaveOrUptRequestBody requestBody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestBody,goods);
        // 3.修改数据
        goodsMapper.update(goods);
    }

    //@Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void remove(RemoveRequestBody requestBody) {
        // 1.遍历删除
        for (Long id : requestBody.getIds()) {
            goodsMapper.delete(id);
        }
    }


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

    @Override
    public GoodsQueryResponseBody queryPage(GoodsQueryRequestBody requestBody) {
        // 1.定义参数
        GoodsQueryResponseBody responseBody = new GoodsQueryResponseBody();
        int total = 0;
        // 2.查询总数
        GoodsQuery goodsQuery = new GoodsQuery();
        BeanUtils.copyProperties(requestBody,goodsQuery);
        total = goodsMapper.total(goodsQuery);
        responseBody.setTotal(total);
        // 3.查询集合
        // 3.1 如何没有数据则直接返回。
        List<GoodsGetResponseBody> getResponseBodies = new ArrayList<>();
        if(responseBody.getTotal()==0){
            responseBody.setRows(getResponseBodies);
            return responseBody;
        }
        // 3.2 有数据
        List<Goods> goodss = goodsMapper.queryPage(goodsQuery);
        for (Goods goods : goodss) {
            GoodsGetResponseBody getResponseBody = new GoodsGetResponseBody();
            BeanUtils.copyProperties(goods, getResponseBody);
            getResponseBodies.add(getResponseBody);
        }
        responseBody.setRows(getResponseBodies);
        return responseBody;
    }
}
