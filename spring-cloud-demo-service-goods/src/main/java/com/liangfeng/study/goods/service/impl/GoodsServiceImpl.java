package com.liangfeng.study.goods.service.impl;


import com.liangfeng.study.api.dto.request.DictApiQueryRequestbody;
import com.liangfeng.study.api.dto.response.DictApiQueryResponsebody;
import com.liangfeng.study.api.service.DictFeignService;
import com.liangfeng.study.core.web.dto.response.Response;
import com.liangfeng.study.goods.model.auto.pojo.Goods;
import com.liangfeng.study.goods.model.auto.qo.GoodsQuery;
import com.liangfeng.study.goods.mapper.GoodsMapper;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.core.web.dto.request.GetRequestbody;
import com.liangfeng.study.core.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.core.web.dto.response.AddResponsebody;
import com.liangfeng.study.goods.web.request.GoodsQueryRequestbody;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import com.liangfeng.study.goods.web.response.GoodsGetResponsebody;
import com.liangfeng.study.goods.web.response.GoodsQueryResponsebody;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsServiceImpl
 * @Description:
 * @date 2018-10-11
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    DictFeignService dictApiService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public AddResponsebody add(GoodsAddOrMdfRequestbody requestbody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestbody,goods);
        // 3.插入数据
        goodsMapper.insert(goods);
        // 4.返回主键
        return new AddResponsebody(goods.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void modify(GoodsAddOrMdfRequestbody requestbody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestbody,goods);
        // 3.修改数据
        goodsMapper.update(goods);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void remove(RemoveRequestbody requestbody) {
        // 1.遍历删除
        for (Long id : requestbody.getIds()) {
            goodsMapper.delete(id);
        }
    }

    @Override
    public GoodsGetResponsebody get(GetRequestbody requestbody) {
        // 1.定义参数
        GoodsGetResponsebody responseBody = new GoodsGetResponsebody();
        // 2.查询对象
        Goods goods = goodsMapper.get(requestbody.getId());
        // 3.复制属性
        BeanUtils.copyProperties(goods,responseBody);
        // 4.返回数据
        return responseBody;
    }
    @Override
    public GoodsQueryResponsebody query(GoodsQueryRequestbody requestbody) {
        // 1.定义参数
        GoodsQueryResponsebody responseBody = new GoodsQueryResponsebody();
        GoodsQuery goodsQuery = new GoodsQuery();
        BeanUtils.copyProperties(requestbody,goodsQuery);

        // 2.查询
        requestbody.setSortColumns("id desc");
        List<Goods> goodss = goodsMapper.query(goodsQuery);
        List<GoodsGetResponsebody> getResponseBodies = responseBody.getRows();
        // 查询字典
        Map<String,String> dictMap = new HashMap<>();
        DictApiQueryRequestbody dictApiQueryRequestbody = new DictApiQueryRequestbody();
        dictApiQueryRequestbody.setSysCode("SCD");
        dictApiQueryRequestbody.setGroupCodes(new String[]{"GOODS_FIRST_TYPE"});
        Response<DictApiQueryResponsebody> response = dictApiService.getDictMap(dictApiQueryRequestbody);
        if (response.isSuccess()) {
            dictMap = response.getResponseBody().getDictMap();
        }
        for (Goods goods : goodss) {
            GoodsGetResponsebody getResponseBody = new GoodsGetResponsebody();
            BeanUtils.copyProperties(goods, getResponseBody);
            getResponseBody.setGoodsType(dictMap.get(getResponseBody.getGoodsType()));
            getResponseBodies.add(getResponseBody);
        }
        return responseBody;
    }


    @Override
    public GoodsQueryResponsebody queryPage(GoodsQueryRequestbody requestbody) {
        // 1.定义参数
        GoodsQueryResponsebody responseBody = new GoodsQueryResponsebody();
        GoodsQuery goodsQuery = new GoodsQuery();
        requestbody.setSortColumns("id desc");
        BeanUtils.copyProperties(requestbody, goodsQuery);

        // 2.使用分页插件分页查询,核心代码就这一行
        PageHelper.startPage(requestbody.getPageNum(), requestbody.getPageSize());
        List<Goods> goodss = goodsMapper.query(goodsQuery);
        PageInfo<Goods> pageInfo = new PageInfo<>(goodss);

        // 3.封装数据并返回
        responseBody.setTotal(pageInfo.getTotal());
        List<GoodsGetResponsebody> getResponseBodies = responseBody.getRows();
        for (Goods goods : goodss) {
            GoodsGetResponsebody getResponseBody = new GoodsGetResponsebody();
            BeanUtils.copyProperties(goods, getResponseBody);
            getResponseBodies.add(getResponseBody);
        }
        return responseBody;
    }
    
}

