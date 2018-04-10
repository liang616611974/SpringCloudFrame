package com.liangfeng.study.goods.service.impl;


import com.liangfeng.study.common.component.id.IdGenerator;
import com.liangfeng.study.common.web.GetRequestBody;
import com.liangfeng.study.common.web.RemoveRequestBody;
import com.liangfeng.study.goods.dao.ext.mapper.GoodsMapperExt;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsQueryRequestBody;
import com.liangfeng.study.goods.web.request.GoodsSaveOrUptRequestBody;
import com.liangfeng.study.goods.web.response.GoodsQueryResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsServiceImpl
 * @Description:
 * @date  2018/4/9 16:50
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    GoodsMapperExt goodsMapperExt;

    @Override
    public void add(GoodsSaveOrUptRequestBody requestBody) {

    }

    @Override
    public void modify(GoodsSaveOrUptRequestBody requestBody) {

    }

    @Override
    public void remove(RemoveRequestBody requestBody) {

    }

    @Override
    public void get(GetRequestBody requestBody) {

    }

    @Override
    public GoodsQueryResponseBody query(GoodsQueryRequestBody requestBody) {
        return null;
    }
}
