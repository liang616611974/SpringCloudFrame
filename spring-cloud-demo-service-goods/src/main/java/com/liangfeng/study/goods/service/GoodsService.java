package com.liangfeng.study.goods.service;


import com.liangfeng.study.core.web.dto.request.GetRequestbody;
import com.liangfeng.study.core.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.core.web.dto.response.AddResponsebody;
import com.liangfeng.study.goods.web.request.GoodsQueryRequestbody;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import com.liangfeng.study.goods.web.response.GoodsGetResponsebody;
import com.liangfeng.study.goods.web.response.GoodsQueryResponsebody;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsService
 * @Description:
 * @date 2018-05-03
 */
public interface GoodsService {

    /**
     * 添加商品
     * @param requestbody
     */
    AddResponsebody add(GoodsAddOrMdfRequestbody requestbody);

    /**
     * 修改商品
     * @param requestbody
     */
    void modify(GoodsAddOrMdfRequestbody requestbody);

    /**
     * 删除商品
     * @param requestbody
     */
    void remove(RemoveRequestbody requestbody);

    /**
     * 获取商品
     * @param requestbody
     * @return
     */
    GoodsGetResponsebody get(GetRequestbody requestbody);

    /**
     * 查询商品
     * @param requestbody
     * @return
     */
    GoodsQueryResponsebody query(GoodsQueryRequestbody requestbody);

    /**
     * 分页查询商品
     * @param requestbody
     */
    GoodsQueryResponsebody queryPage(GoodsQueryRequestbody requestbody);

}

