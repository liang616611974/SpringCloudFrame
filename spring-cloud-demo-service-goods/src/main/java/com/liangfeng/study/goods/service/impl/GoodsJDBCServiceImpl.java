package com.liangfeng.study.goods.service.impl;


import com.liangfeng.study.common.web.dto.request.GetRequestbody;
import com.liangfeng.study.common.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.goods.dao.auto.mapper.GoodsMapper;
import com.liangfeng.study.goods.dao.auto.model.pojo.Goods;
import com.liangfeng.study.goods.dao.auto.model.qo.GoodsQuery;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsQueryPageRequestbody;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import com.liangfeng.study.goods.web.response.GoodsGetResponsebody;
import com.liangfeng.study.goods.web.response.GoodsQueryPageResponsebody;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void add(GoodsAddOrMdfRequestbody requestBody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestBody,goods);
        // 3.插入数据
        goodsMapper.insert(goods);
    }

    //@Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void modify(GoodsAddOrMdfRequestbody requestBody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestBody,goods);
        // 3.修改数据
        goodsMapper.update(goods);
    }

    //@Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void remove(RemoveRequestbody requestBody) {
        // 1.遍历删除
        for (Long id : requestBody.getIds()) {
            goodsMapper.delete(id);
        }
    }


    @Override
    public GoodsGetResponsebody get(GetRequestbody requestBody) {
        // 1.定义参数
        GoodsGetResponsebody responseBody = new GoodsGetResponsebody();
        // 2.查询对象
        Goods goods = goodsMapper.get(requestBody.getId());
        // 3.复制属性
        BeanUtils.copyProperties(goods,responseBody);
        // 4.返回数据
        return responseBody;
    }

    @Override
    public GoodsQueryPageResponsebody queryPage(GoodsQueryPageRequestbody requestBody) {
        // 1.定义参数
        GoodsQueryPageResponsebody responseBody = new GoodsQueryPageResponsebody();
        int total = 0;
        // 2.查询总数
        GoodsQuery goodsQuery = new GoodsQuery();
        BeanUtils.copyProperties(requestBody,goodsQuery);
        total = goodsMapper.total(goodsQuery);
        responseBody.setTotal(total);
        // 3.查询集合
        // 3.1 如何没有数据则直接返回。
        List<GoodsGetResponsebody> getResponseBodies = new ArrayList<>();
        if(responseBody.getTotal()==0){
            responseBody.setRows(getResponseBodies);
            return responseBody;
        }
        // 3.2 有数据
        List<Goods> goodss = goodsMapper.queryPage(goodsQuery);
        for (Goods goods : goodss) {
            GoodsGetResponsebody getResponseBody = new GoodsGetResponsebody();
            BeanUtils.copyProperties(goods, getResponseBody);
            getResponseBodies.add(getResponseBody);
        }
        responseBody.setRows(getResponseBodies);
        return responseBody;
    }
}
