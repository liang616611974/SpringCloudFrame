package com.liangfeng.study.goods.service.impl;


import com.liangfeng.study.common.helper.DateHelper;
import com.liangfeng.study.common.web.dto.request.GetRequestbody;
import com.liangfeng.study.common.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.goods.dao.auto.model.pojo.Goods;
import com.liangfeng.study.goods.dao.auto.model.qo.GoodsQuery;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsQueryPageRequestbody;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import com.liangfeng.study.goods.web.response.GoodsGetResponsebody;
import com.liangfeng.study.goods.web.response.GoodsQueryResponsebody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsServiceImpl
 * @Description:
 * @date  2018/4/9 16:50
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(GoodsAddOrMdfRequestbody requestBody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestBody, goods);
        // 3.插入数据
        //goodsMapper.insert(goods);
        logger.info("Goods:{}",goods);

    }

    @Override
    public void modify(GoodsAddOrMdfRequestbody requestBody) {
        // 1.定义参数
        Goods goods = new Goods();
        // 2.复制属性值
        BeanUtils.copyProperties(requestBody, goods);
        // 3.修改数据
        //goodsMapper.update(goods);
    }

    @Override
    public void remove(RemoveRequestbody requestBody) {
        // 1.遍历删除
        for (Long id : requestBody.getIds()) {
            //goodsMapper.delete(id);
        }
    }


    @Override
    public GoodsGetResponsebody get(GetRequestbody requestBody) {
        // 1.定义参数
        GoodsGetResponsebody responseBody = new GoodsGetResponsebody();
        Goods goods = null;
        // 2.查询对象
        //goods = goodsMapper.get(requestBody.getId());
        goods = new Goods();
        goods.setId(new Long(666666));
        goods.setGoodsName("耐克跑鞋");
        goods.setType("运动系列");
        goods.setPrice(new BigDecimal("899"));
        goods.setProducerName("耐克集团");
        goods.setProduceDate(DateHelper.parseDate("2018-03-04"));
        goods.setCreateTime(DateHelper.parseDateTime("2018-03-04 09:00:00"));
        goods.setModifyTime(DateHelper.parseDateTime("2018-03-04 09:00:00"));
        goods.setCreateUser(new Long(111));
        goods.setCreateUser(new Long(222));
        // 3.复制属性
        BeanUtils.copyProperties(goods, responseBody);
        // 4.返回数据
        return responseBody;
    }

    @Override
    public GoodsQueryResponsebody queryPage(GoodsQueryPageRequestbody requestBody) {
        // 1.定义参数
        GoodsQueryResponsebody responseBody = new GoodsQueryResponsebody();
        int total = 10;
        // 2.查询总数
        GoodsQuery goodsQuery = new GoodsQuery();
        BeanUtils.copyProperties(requestBody, goodsQuery);
        //total = goodsMapper.total(goodsQuery);
        responseBody.setTotal(total);
        // 3.查询集合
        // 3.1 如何没有数据则直接返回。
        List<GoodsGetResponsebody> getResponseBodies = new ArrayList<>();
        if (responseBody.getTotal() == 0) {
            responseBody.setRows(getResponseBodies);
            return responseBody;
        }
        // 3.2 有数据
        List<Goods> goodss = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Goods goods = new Goods();
            goods.setId(new Long(666666) + i);
            goods.setGoodsName("耐克跑鞋" + i);
            goods.setType("运动系列");
            goods.setPrice(new BigDecimal("899"));
            goods.setProducerName("耐克集团");
            goods.setProduceDate(DateHelper.parseDate("2018-03-04"));
            goods.setCreateTime(DateHelper.parseDateTime("2018-03-04 09:00:00"));
            goods.setModifyTime(DateHelper.parseDateTime("2018-03-04 09:00:00"));
            goods.setCreateUser(new Long(111));
            goods.setCreateUser(new Long(222));
            goodss.add(goods);
        }
        for (Goods goods : goodss) {
            GoodsGetResponsebody getResponseBody = new GoodsGetResponsebody();
            BeanUtils.copyProperties(goods, getResponseBody);
            getResponseBodies.add(getResponseBody);
        }
        responseBody.setRows(getResponseBodies);
        return responseBody;
    }
}
