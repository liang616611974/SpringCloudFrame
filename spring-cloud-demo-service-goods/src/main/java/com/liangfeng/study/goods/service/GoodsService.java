package com.liangfeng.study.goods.service;

import com.liangfeng.study.core.web.dto.request.GetRequestbody;
import com.liangfeng.study.core.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.goods.web.request.GoodsQueryPageRequestbody;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import com.liangfeng.study.goods.web.response.GoodsGetResponsebody;
import com.liangfeng.study.goods.web.response.GoodsQueryPageResponsebody;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsService
 * @Description:
 * @date  2018/4/9 16:50
 */
public interface GoodsService {

    /**
     * 添加商品
     * @param requestBody
     */
    void add(GoodsAddOrMdfRequestbody requestBody);

    /**
     * 修改商品
     * @param requestBody
     */
    void modify(GoodsAddOrMdfRequestbody requestBody);

    /**
     * 删除商品
     * @param requestBody
     */
    void remove(RemoveRequestbody requestBody);

    /**
     * 获取商品
     * @param requestBody
     * @return
     */
    GoodsGetResponsebody get(GetRequestbody requestBody);

    /**
     * 分页查询商品
     * @param requestBody
     */
    GoodsQueryPageResponsebody queryPage(GoodsQueryPageRequestbody requestBody);

}
