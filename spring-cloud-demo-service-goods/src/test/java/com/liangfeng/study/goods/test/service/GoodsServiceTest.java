package com.liangfeng.study.goods.test.service;


import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.framework.base.BaseTest;
import com.liangfeng.study.core.helper.ObjectHelper;
import com.liangfeng.study.core.web.dto.request.GetRequestbody;
import com.liangfeng.study.core.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.core.web.dto.response.AddResponsebody;
import com.liangfeng.study.goods.model.auto.pojo.Goods;
import com.liangfeng.study.goods.mapper.GoodsMapper;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import com.liangfeng.study.goods.web.request.GoodsQueryRequestbody;
import com.liangfeng.study.goods.web.response.GoodsGetResponsebody;
import com.liangfeng.study.goods.web.response.GoodsQueryResponsebody;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsServiceTest
 * @Description:
 * @date 2018-06-09
 */
@Transactional
@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest extends BaseTest{

    // 不需要校验的字段名称数组
    private static final String[] noneValidFileds = {AppConstant.SESSION_ATTR_NAME_USERID, AppConstant.SESSION_ATTR_NAME_USERROLES};

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsService service;

    @Before
    public void before() {
        // 1.清除已有的数据，避免影响测试
        List<Goods> goodss = goodsMapper.query(null);
        for (Goods goods : goodss) {
            goodsMapper.delete(goods.getId());
        }
    }

    @Test
    public void testAdd() {
        // 1.定义参数
        GoodsAddOrMdfRequestbody requestbody;
        AddResponsebody responsebody;
        Goods goods;
        String errMsg = "测试添加商品失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        requestbody = new GoodsAddOrMdfRequestbody();
        ObjectHelper.initFieldsVal(requestbody, 1);
        // 执行
        responsebody = service.add(requestbody);
        // 验证结果
        goods = goodsMapper.get(requestbody.getId());
        compareObjProps(errMsg, requestbody, goods, noneValidFileds);
        Assert.assertEquals(errMsg, requestbody.getId(), responsebody.getId());
    }

    @Test
    public void testModify() {
        // 1.定义参数
        GoodsAddOrMdfRequestbody requestbody;
        Goods goods;
        String errMsg = "测试修改商品失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        goods = new Goods();
        ObjectHelper.initFieldsVal(goods, 1);
        goodsMapper.insert(goods);
        requestbody = new GoodsAddOrMdfRequestbody();
        ObjectHelper.initFieldsVal(requestbody, 2);
        requestbody.setId(goods.getId());
        // 运行
        service.modify(requestbody);
        // 验证结果
        goods = goodsMapper.get(requestbody.getId());
        compareObjProps(errMsg, requestbody, goods, noneValidFileds);
    }

    @Test
    public void testRemove() {
        // 1.定义参数
        RemoveRequestbody requestbody;
        Goods goods;
        String errMsg = "测试删除商品失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        requestbody = new RemoveRequestbody();
        for (int i = 1; i <= 3; i++) {
            goods = new Goods();
            ObjectHelper.initFieldsVal(goods, i);
            goodsMapper.insert(goods);
            requestbody.add(goods.getId());
        }
        // 运行
        service.remove(requestbody);
        List<Goods> goodss = goodsMapper.query(null);
        Assert.assertEquals(errMsg, 0, goodss.size());
    }

    @Test
    public void testGet() {
        // 1.定义参数
        GetRequestbody requestbody;
        GoodsGetResponsebody responsebody;
        Goods goods;
        String errMsg = "测试获取商品失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        goods = new Goods();
        ObjectHelper.initFieldsVal(goods, 1);
        goodsMapper.insert(goods);
        requestbody = new GetRequestbody();
        requestbody.setId(goods.getId());
        // 运行
        responsebody = service.get(requestbody);
        // 验证结果
        compareObjProps(errMsg, goods, responsebody, noneValidFileds);
    }

    @Test
    public void testQuery() {
        // 1.定义参数
        GoodsQueryRequestbody requestbody;
        GoodsQueryResponsebody responsebody;
        Goods goods;
        String errMsg = "测试查询商品失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        List<Goods> goodss = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            goods = new Goods();
            ObjectHelper.initFieldsVal(goods, i);
            goodsMapper.insert(goods);
            goodss.add(goods);
        }
        requestbody = new GoodsQueryRequestbody();
        // 运行
        responsebody = service.query(requestbody);
        // 验证结果
        List<GoodsGetResponsebody> getResponsebodies = responsebody.getRows();
        for (int i = 0; i < 3; i++) {
            compareObjProps(errMsg, goodss.get(i), getResponsebodies.get(i));
        }
    }

    @Test
    public void testQueryPage() {
        // 1.定义参数
        GoodsQueryRequestbody requestbody;
        GoodsQueryResponsebody responsebody;
        Goods goods;
        String errMsg = "测试查询商品失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        List<Goods> goodss = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            goods = new Goods();
            ObjectHelper.initFieldsVal(goods, i);
            goodsMapper.insert(goods);
            goodss.add(goods);
        }
        requestbody = new GoodsQueryRequestbody();
        requestbody.setPageNum(1);
        requestbody.setPageSize(1);
        requestbody.setSortColumns("id desc");
        // 运行
        responsebody = service.queryPage(requestbody);
        // 验证结果
        Assert.assertEquals(errMsg, 3, responsebody.getTotal());
        Assert.assertEquals(errMsg, 1, responsebody.getRows().size());
        compareObjProps(errMsg, goodss.get(2), responsebody.getRows().get(0));
    }

}
