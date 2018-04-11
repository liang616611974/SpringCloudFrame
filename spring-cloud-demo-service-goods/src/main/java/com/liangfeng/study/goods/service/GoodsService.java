package com.liangfeng.study.goods.service;

import com.liangfeng.study.common.web.dto.GetRequestBody;
import com.liangfeng.study.common.web.dto.RemoveRequestBody;
import com.liangfeng.study.goods.web.request.GoodsQueryRequestBody;
import com.liangfeng.study.goods.web.request.GoodsSaveOrUptRequestBody;
import com.liangfeng.study.goods.web.response.GoodsGetResponseBody;
import com.liangfeng.study.goods.web.response.GoodsQueryResponseBody;

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
    void add(GoodsSaveOrUptRequestBody requestBody);

    /**
     * 修改商品
     * @param requestBody
     */
    void modify(GoodsSaveOrUptRequestBody requestBody);

    /**
     * 删除商品
     * @param requestBody
     */
    void remove(RemoveRequestBody requestBody);

    /**
     * 获取商品
     * @param requestBody
     * @return
     */
    GoodsGetResponseBody get(GetRequestBody requestBody);

    /**
     * 查询商品
     * @param requestBody
     */
    GoodsQueryResponseBody query(GoodsQueryRequestBody requestBody);

}
